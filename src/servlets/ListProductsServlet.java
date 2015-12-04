package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.ArrayList;

import beans.Product;
import data.*;

@WebServlet("/searchProducts")
@ServletSecurity(
		value = @HttpConstraint(value = EmptyRoleSemantic.DENY),
		httpMethodConstraints = {@HttpMethodConstraint(value = "GET"),
			                     @HttpMethodConstraint(value = "POST")})
public class ListProductsServlet extends HttpServlet {

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

    	// Get the search string and get results from DB
    	String searchString = request.getParameter("searchString");
        ArrayList<Product> products = ProductDB.searchProducts(searchString);
        
        // Store the products in the request
        request.setAttribute("products", products);

        // Forward to JSP
        String url = "/WEB-INF/pages/products.jsp";
        RequestDispatcher dispatcher =
              getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}