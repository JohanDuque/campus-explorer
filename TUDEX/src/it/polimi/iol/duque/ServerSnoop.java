package it.polimi.iol.duque;

import java.io.*;                                                    
import java.util.*;                                                  
import javax.servlet.*;                                              
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
                                                                     
public class ServerSnoop extends HttpServlet {                    
                                                                     
	  /**
	 * 
	 */
	private static final long serialVersionUID = 8811124016554271426L;

	  private String getServerInfoName(String serverInfo) {
		  int slash = serverInfo.indexOf('/');
		  if (slash == -1) return serverInfo;
		  	else return serverInfo.substring(0, slash);
	  }
	
	  private String getServerInfoVersion(String serverInfo) {
	    // Version info is everything between the slash and the space
	    int slash = serverInfo.indexOf('/');
	    if (slash == -1) return null;
	    int space = serverInfo.indexOf(' ', slash);
	    if (space == -1) space = serverInfo.length();
	    return serverInfo.substring(slash + 1, space);
	 }
	  
	  public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
		  	response.setContentType("text/html");
		  	
		  	PrintWriter out = response.getWriter();
		  	
		  	String title = "Servlet Example: Showing Request Headers";
		  	
		  	out.println("<HTML><HEAD><TITLE>" + title + " </TITLE></HEAD>" + 
		  					"<BODY BGCOLOR=\"#FDF5E6\">\n" +
		  					"<H1 ALIGN=CENTER>" + title + "</H1>\n" +
		  					"<B>Request Method: </B>" + request.getMethod() + "<BR>\n" +
		  					"<B>Request URI: </B>" + request.getRequestURI() + "<BR>\n" +
		  					"<B>Request Protocol: </B>" + request.getProtocol() + "<BR><BR>\n" +
		  					"<TABLE BORDER=1 ALIGN=CENTER>\n" +
		  					"<TR BGCOLOR=\"#FFAD00\">\n" +
		  					"<TH>Header Name<TH>Header Value");

		  	Enumeration headerNames = request.getHeaderNames();
		  		
		  	while(headerNames.hasMoreElements()) {
		  			String headerName = (String)headerNames.nextElement();
		  			out.println("<TR><TD>" + headerName);
		  			out.println("    <TD>" + request.getHeader(headerName));
		  	}
		  	
		  	out.println("</TABLE>");
		  		
		  	ServletContext context = getServletContext();	    	    
			
		  	out.println("req.getServerName(): " + request.getServerName());      
			
		  	out.println("req.getServerPort(): " + request.getServerPort());
			
		  	out.println("context.getServerInfo(): " + context.getServerInfo());
			
		  	out.println("getServerInfo() name: " + getServerInfoName(context.getServerInfo()));
			
		  	out.println("getServerInfo() version: " + getServerInfoVersion(context.getServerInfo()));
			    
		  	out.println("context.getAttributeNames():");
			
		  	Enumeration enume = context.getAttributeNames();	    
			
		  	while (enume.hasMoreElements()) {
			      String name = (String) enume.nextElement();
			      out.println("  context.getAttribute(\"" + name + "\"): " + context.getAttribute(name));
			}	    	   
		  		
		  	out.println("</BODY></HTML>");
		  		
	  }

	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
	  }

}
