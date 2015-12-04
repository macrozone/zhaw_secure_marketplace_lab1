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

import util.CSRF;
import data.*;

@WebServlet("/admin/completepurchase")
@ServletSecurity(
		value = @HttpConstraint(value = EmptyRoleSemantic.DENY),
		httpMethodConstraints = {@HttpMethodConstraint(value = "GET", 
						         rolesAllowed = {"marketing", "sales"},
						         transportGuarantee = TransportGuarantee.CONFIDENTIAL),
						         @HttpMethodConstraint(value = "POST",
						         rolesAllowed = {"marketing", "sales"}, 
						         transportGuarantee = TransportGuarantee.CONFIDENTIAL)})
public class DeletePurchaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP POST method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

	/**
     * Processes requests for both HTTP GET and POST methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String message;
    	
    	// Check the CSRF token
		String tokenReceived = request.getParameter("csrftoken");
		HttpSession session = request.getSession();
		String tokenExpected = (String) session.getAttribute("csrftoken");
		if (tokenReceived == null || !CSRF.validateToken(tokenReceived, tokenExpected)) {
			message = "Token mismatch, expected: " + tokenExpected + ", received: " + tokenReceived;
		} else {
			
			// Token is valid, check the rights of the user
			if (!request.isUserInRole("sales")) {
				message = "You are not allowed to perform this operation";
			} else {
				
				// Get the purchaseId to delete and delete it
				try {
					int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
					if (PurchaseDB.delete(purchaseId) != 1) {
						throw new Exception();
					}
					message = "Purchase completed";
				} catch (Exception e) {
					e.printStackTrace();
					message = "A problem occurred when completing the purchase";
				}
			}
		}
    	request.setAttribute("message", message);
        
        // Forward to servlet
        String url = "/admin/listpurchases";
        RequestDispatcher dispatcher =
              getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}