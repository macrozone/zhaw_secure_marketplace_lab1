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
import beans.*;
import util.Validation;
import util.CSRF;

@WebServlet("/admin/storeproduct")
public class StoreProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String message;
    	String url;
    	
    	// Get parameters from the request
        String productCode = request.getParameter("productCode");
        String description = request.getParameter("description");        
        String price = request.getParameter("price");
        
        // Validate the received data, implement...
        
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
        	
        request.setAttribute("message", message);
        RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
