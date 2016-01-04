package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.owasp.esapi.ValidationErrorList;
import org.owasp.esapi.errors.ValidationException;

import data.*;
import beans.*;
import util.CSRF;
import util.Validation;

@WebServlet("/purchase")
@ServletSecurity(value = @HttpConstraint(value = EmptyRoleSemantic.DENY), httpMethodConstraints = {
		@HttpMethodConstraint(value = "GET", transportGuarantee = TransportGuarantee.CONFIDENTIAL),
		@HttpMethodConstraint(value = "POST", transportGuarantee = TransportGuarantee.CONFIDENTIAL) })
public class PurchaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Check if there's any content in the cart. If not, forward the user to
		 * the cart servlet and display a message
		 */
		HttpSession session = request.getSession();
		String url = "";
		String message = "";
		Cart cart = (Cart) session.getAttribute("cart");
		String tokenReceived = request.getParameter("csrftoken");
		String tokenExpected = (String) session.getAttribute("csrftoken");
		if (tokenReceived == null
				|| !CSRF.validateToken(tokenReceived, tokenExpected)) {
			message = "Token mismatch, expected: " + tokenExpected
					+ ", received: " + tokenReceived;
		} else {
			if ((cart == null) || (cart.getCount() == 0)) {
				message = "Your cart is empty, so don't you think checking out is pointless?";
				url = "/cart";
			} else {

				// Get parameters from the request
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String ccNumber = request.getParameter("ccNumber");

				// Validate the received data
				ValidationErrorList errors = new ValidationErrorList();
				if ((firstName = Validation.validatePersonName(firstName)) == null) {
					message = "Please insert a valid first name";
					url = "/checkout";
				} else if ((lastName = Validation.validatePersonName(lastName)) == null) {
					message = "Please insert a valid last name";
					url = "/checkout";
				} else if ((ccNumber = Validation.validateCCNumber(ccNumber,
						errors)) == null) {
					message = "Please insert a valid credit card number";
					url = "/checkout";
					for (Object vo : errors.errors()) {
						log("Error (validateCCNumber): "
								+ ((ValidationException) vo).getMessage());
					}
				} else {

					// Store purchase in DB
					Purchase purchase = new Purchase();
					purchase.setFirstName(firstName);
					purchase.setLastName(lastName);
					purchase.setCcNumber(ccNumber);
					purchase.setTotalPrice(cart.getTotal());
					if (PurchaseDB.insert(purchase) < 1) {
						message = "A problem occurred, please try again later.";
					} else {
						message = "Your purchase has been completed, thank you for shopping with us.";
					}
					session.setAttribute("cart", null);
					url = "/index.jsp";
				}
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
