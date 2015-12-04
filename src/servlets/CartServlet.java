package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.http.*;

import beans.*;
import data.*;

@WebServlet("/cart")
@ServletSecurity(
		value = @HttpConstraint(value = EmptyRoleSemantic.DENY),
		httpMethodConstraints = {@HttpMethodConstraint(value = "GET"),
			                     @HttpMethodConstraint(value = "POST")})
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // If a product was specified, add it to the cart
        String productCode = request.getParameter("productCode");
        if (productCode != null) {
        	
        	// If the session does not contain a cart, create it
        	HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
        	Product product = ProductDB.getProduct(productCode);
        	
    			// Only add product to cart if one was actually read from the DB
    			if (product != null) {
    				LineItem lineItem = new LineItem();
    				lineItem.setProduct(product);
    				cart.addItem(lineItem);
    			}
    			session.setAttribute("cart", cart);        
    		}

        // Forward to JSP
        String url = "/WEB-INF/pages/cart.jsp";
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
