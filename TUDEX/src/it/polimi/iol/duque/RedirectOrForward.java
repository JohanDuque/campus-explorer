package it.polimi.iol.duque;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RedirectOrForward extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
	res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    if ("2".equals(req.getParameter("mode"))){
        RequestDispatcher rdisp = this.getServletContext().getRequestDispatcher("/indice.jsp");
        rdisp.forward(req, res);       
    } else {
        res.sendRedirect("http://www.polimi.it");
    }
  }
}
