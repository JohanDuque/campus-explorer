package it.polimi.iol.duque;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SessionTracker extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -6394668809126624412L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	                               throws ServletException, IOException {
	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
	
	    // Get the current session object, create one if necessary
	    //!!!! CHECK IF YOUR SERVLET CONTAINER IS CONFIGURED TO ALLOW URL-REWRITING
	    HttpSession session = req.getSession(true);	    	    	    	   
	    	
	    // Increment the hit count for this page. The value is saved
	    // in this client's session under the name "tracker.count".
	    Integer count = (Integer)session.getAttribute("tracker.count");
	    if (count == null)
	      count = new Integer(1);
	    else
	      count = new Integer(count.intValue() + 1);
	    session.setAttribute("tracker.count", count);
	
	    out.println("<HTML><HEAD><TITLE>SessionTracker</TITLE></HEAD>");
	    out.println("<BODY><H1>Session Tracking Demo</H1>");
	
	    // Display the hit count for this page
	    out.println("You've visited this page " + count +
	      ((count.intValue() == 1) ? " time." : " times."));
	
	    out.println("<P>");
	
	    out.println("<H2>Here is your session data:</H2>");
	    Enumeration enume = session.getAttributeNames();
	    while (enume.hasMoreElements()) {
	      String name = (String) enume.nextElement();
	      out.println(name + ": " + session.getAttribute(name) + "<BR>");
	    }
	    
	    out.println("<a href=" + res.encodeURL(req.getRequestURI()) + ">here</a> <br />");   
	    out.println("Using cookie? " + req.isRequestedSessionIdFromCookie() + "<br />");
	    out.println("Using URL rewriting? " + req.isRequestedSessionIdFromURL() + "<br />");
	    
	    out.println("</BODY></HTML>");
	  }
}
