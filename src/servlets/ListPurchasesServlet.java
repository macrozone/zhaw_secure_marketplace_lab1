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

import java.util.ArrayList;

import beans.Purchase;
import data.*;

@WebServlet("/admin/listpurchases")
@ServletSecurity(
		value = @HttpConstraint(value = EmptyRoleSemantic.DENY),
		httpMethodConstraints = {@HttpMethodConstraint(value = "GET", 
						         rolesAllowed = {"marketing", "sales"},
						         transportGuarantee = TransportGuarantee.CONFIDENTIAL),
						         @HttpMethodConstraint(value = "POST",
						         rolesAllowed = {"marketing", "sales"}, 
						         transportGuarantee = TransportGuarantee.CONFIDENTIAL)})
public class ListPurchasesServlet extends HttpServlet {

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

    	// Get all purchases from DB
        ArrayList<Purchase> purchases = PurchaseDB.getPurchases();
        
        // Store the purchases in the request
        request.setAttribute("purchases", purchases);
        
        // Check if the user is allowed to complete purchases
        if (request.isUserInRole("sales")) {
        	request.setAttribute("completeAllowed", true);
        } else {
        	request.setAttribute("completeAllowed", false);
        }

        // Forward to JSP
        String url = "/WEB-INF/pages/admin/purchases.jsp";
        RequestDispatcher dispatcher =
              getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}