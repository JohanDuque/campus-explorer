package it.polimi.iol.duque;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShoppingCartViewerSession extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -5470770429167227624L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	                               throws ServletException, IOException {
	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
	
	    // Get the current session object, create one if necessary.
	    HttpSession session = req.getSession();
	
	    // Cart items are maintained in the session object.
	    String[] items = (String[])session.getAttribute("cart.items");
	
	    out.println("<HTML><HEAD><TITLE>Shopping Cart (Session)</TITLE></HEAD>");
	    out.println("<BODY><H1>Shopping Cart (Session)</H1>");
	
	    // Print the current cart items.
	    out.println("You currently have the following items in your cart:<BR>");
	    if (items == null) {
	      out.println("<B>None</B>");
	    }
	    else {
	      out.println("<UL>");
	      for (int i = 0; i < items.length; i++) {
	        out.println("<LI>" + items[i]);
	      }
	      out.println("</UL>");
	    }
	   
	    out.println("</BODY></HTML>");
	  }
	
		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doGet(req,res);
		}
}
