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

import beans.Cart;

@WebServlet("/checkout")
@ServletSecurity(
		value = @HttpConstraint(value = EmptyRoleSemantic.DENY),
		httpMethodConstraints = {@HttpMethodConstraint(value = "GET", 
		                         transportGuarantee = TransportGuarantee.CONFIDENTIAL),
			                     @HttpMethodConstraint(value = "POST",
			                     transportGuarantee = TransportGuarantee.CONFIDENTIAL)})
public class CheckoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	/* Check if there's any content in the cart. If not, forward the
    	   user to the cart servlet and display a message */
    	HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if ((cart == null) || (cart.getCount() == 0)) {
        	request.setAttribute("message", "Your cart is empty, so don't you think checking out is pointless?");
        	String url = "/cart";
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
        	// Forward to JSP
        	String url = "/WEB-INF/pages/checkout.jsp";
        	RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        	dispatcher.forward(request, response);
        }
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
