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
import util.Crypto;
import data.LoginDB;

@WebServlet("/admin/login")
@ServletSecurity(
		value = @HttpConstraint(value = EmptyRoleSemantic.DENY),
		httpMethodConstraints = {@HttpMethodConstraint(value = "GET", 
		                         transportGuarantee = TransportGuarantee.CONFIDENTIAL),
			                     @HttpMethodConstraint(value = "POST",
			                     transportGuarantee = TransportGuarantee.CONFIDENTIAL)})
public class LoginServlet extends HttpServlet {
	
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
    	
    	// Check if the request contains parameters 
    	String url = null;
    	if (request.getParameterNames().hasMoreElements() == false)  {
    		
    		// If there are no parameters, the request comes from clicking
    	    // the "Admin area" button. Check if the user is already logged in
    		if (request.isUserInRole("sales") || request.isUserInRole("marketing")) {
    			
    			// Logged in, forward to servlet
    	        url = "/admin/listpurchases";
    		} else if (request.isUserInRole("productmanager")) {
    			url = "/admin/manageproducts";
    		} else {
    			
    			// Not logged in, forward to login form
    	        url = "/WEB-INF/pages/admin/login.jsp";
    		}
	        RequestDispatcher dispatcher =
	              getServletContext().getRequestDispatcher(url);
	        dispatcher.forward(request, response);

    	} else {
    		
    		// Parameters have been submitted, the request comes from the
    		// admin form. Check submitted username and password
     		String username = request.getParameter("username");
    		String password = request.getParameter("password");
    	
    		// Authenticate the user
    		try {
    		
    		  // Read the salt from the DB and compute the digest
  				String salt = LoginDB.getSalt(username);   		
  				String digest = Crypto.computeScryptHash(password, salt);
  				request.login(username, digest);
    			
    			// Create the CSRF token and store it in the session
    			String csrfToken = CSRF.createToken();
            	HttpSession session = request.getSession();
            	session.setAttribute("csrftoken", csrfToken);
            	
            	if (request.isUserInRole("productmanager")) {
        			url = "/admin/manageproducts";
        		} else {
        			url = "/admin/listpurchases";
        		}
            	
     		} catch (Exception e) {
     			request.setAttribute("message", "Login failed.");
     			url = "/index.jsp";
    		} 
	        RequestDispatcher dispatcher =
	              getServletContext().getRequestDispatcher(url);
	        dispatcher.forward(request, response);
    	}
    }    
}
