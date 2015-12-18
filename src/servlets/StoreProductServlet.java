package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ValidationErrorList;
import org.owasp.esapi.errors.ValidationException;

import util.Validation;
import beans.Product;
import data.ProductDB;

@WebServlet("/admin/storeproduct")
public class StoreProductServlet extends HttpServlet {

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

		String message;
		String url;

		// Get parameters from the request
		String productCode = request.getParameter("productCode");
		String description = request.getParameter("description");
		String price = request.getParameter("price");

		// Validate the received data, implement...
		// Validate the received data
		ValidationErrorList errors = new ValidationErrorList();
		if ((productCode = Validation.validateProductCode(productCode)) == null) {
			message = "Please insert a valid product code";
			url = "/admin/addproduct";
		} else if ((description = Validation
				.validateProductDescription(description)) == null) {
			message = "Please insert a valid product description";
			url = "/admin/addproduct";
		} else if ((price = Validation.validateProductPrice(price)) == null) {
			message = "Please insert a valid price";
			url = "/admin/addproduct";
			for (Object vo : errors.errors()) {
				log("Error (validateCCNumber): "
						+ ((ValidationException) vo).getMessage());
			}
		} else {
			// Store product in DB
			Product product = new Product();
			product.setCode(productCode);
			product.setDescription(description);
			product.setPrice(Double.parseDouble(price));
			if (ProductDB.insertProduct(product) < 1) {
				message = "A problem occurred, please try again later.";
			} else {
				message = "The product has been added.";
			}
			url = "/admin/manageproducts";

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
