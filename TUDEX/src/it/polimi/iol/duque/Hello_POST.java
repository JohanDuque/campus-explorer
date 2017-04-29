package it.polimi.iol.duque;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Hello_POST extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 7710556062349753891L;

		public void doGet(HttpServletRequest req, HttpServletResponse res)
	                               throws ServletException, IOException {
			doPost(req, res);                             	
	    }
	  
	  public void doPost(HttpServletRequest req, HttpServletResponse res)
	                               throws ServletException, IOException {
	
	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
	
	    String name = req.getParameter("name");
	    out.println("<HTML>");
	    out.println("<HEAD><TITLE>Hello, " + name + "</TITLE></HEAD>");
	    out.println("<BODY>");
	    out.println("Hello, " + name);
	    out.println("</BODY></HTML>");
	  }
	
	  public String getServletInfo() {
	    return "A servlet that knows the name of the person to whom it's" + 
	           "saying hello";
	  }
}