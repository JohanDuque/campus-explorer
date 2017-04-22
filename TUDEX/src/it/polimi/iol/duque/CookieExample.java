package it.polimi.iol.duque;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CookieExample extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body bgcolor=\"white\">");
        out.println("<head>");

        String title = "Cookies";
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h3>" + title + "</h3>");

        Cookie[] cookies = request.getCookies();
        
        if ((cookies != null) && (cookies.length > 0)) {
            out.println("Il tuo browser sta inviando questi Cookie" + "<br>");
           
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                out.print("Cookie Name: " + cookie.getName()
                          + "<br>");
                out.println("  Cookie Value: " 
                            + cookie.getValue()
                            + "<br><br>");
            }
        } else {
            out.println("Il tuo browser non sta inviando Cookie");
        }

        String cookieName = request.getParameter("cookiename");
        String cookieValue = request.getParameter("cookievalue");
        
        if (cookieName != null && cookieValue != null) {
            Cookie cookie = new Cookie(cookieName, cookieValue);
            response.addCookie(cookie);
            out.println("<P>");
            out.println("Il server ha appena mandato questo Cookie al tuo browser" + "<br>");
            out.print("Cookie Name:" + "  " 
                      + cookieName + "<br>");
            out.print("Cookie Value:"  + "  " +
                      cookieValue);
        }
        
        out.println("<P>");
        out.println("Crea un cookie" + "<br>");
        out.print("<form action=\"");
        out.println("CookieExample\" method=POST>");
        out.print("Cookie Name:" + "  ");
        out.println("<input type=text length=20 name=cookiename><br>");
        out.print("Cookie Value:" + "  ");
        out.println("<input type=text length=20 name=cookievalue><br>");
        out.println("<input type=submit></form>");
            
            
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException
    {
        doGet(request, response);
    }

}


