package it.polimi.iol.duque;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ShoppingCartViewerHidden extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -4381071910712373197L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	                               throws ServletException, IOException {
	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
	
	    out.println("<HTML><HEAD><TITLE>Shopping Cart (Request)</TITLE></HEAD>");
	    out.println("<BODY><H1>Shopping Cart (Request)</H1>");
	
	    // Cart items are passed in as the item parameter.
	    String[] items = req.getParameterValues("item");
	    	
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
	    
	    //	  Get the current session object, create one if necessary.
	    HttpSession session = req.getSession();
	
	    // Cart items are maintained in the session object.
	    session.setAttribute("cart.items",items);
	    	
	    // Show the cart item saved in the session
	    // Include the current items as hidden fields so they'll be passed on.
	    out.println("<FORM ACTION=\"/SL2/shopView\" METHOD=POST>");
	    out.println("<INPUT TYPE=SUBMIT VALUE=\" Show Session Items \">");	    
	    out.println("</FORM>");
	
	    out.println("</BODY></HTML>");
	  }
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
doGet(req,res);
}
}
