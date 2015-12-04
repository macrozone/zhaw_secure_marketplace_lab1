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

import data.*;
import util.CSRF;

@WebServlet("/admin/deleteproduct")
public class DeleteProductServlet extends HttpServlet {

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

    	// Get the productCode to delete and delete it
    	try {
    		String productCode = request.getParameter("productCode");
  		  	if (ProductDB.deleteProduct(productCode) != 1) {
  		  		throw new Exception();
  		  	}
  		  	message = "Product deleted";
  	  	} catch (Exception e) {
  	  		e.printStackTrace();
  	  		message = "A problem occurred when deleting the product";
  	  	}
    	request.setAttribute("message", message);
        
        // Forward to servlet
        String url = "/admin/manageproducts";
        RequestDispatcher dispatcher =
              getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}