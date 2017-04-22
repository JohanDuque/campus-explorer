package it.polimi.iol.duque;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreaMessaggio extends HttpServlet 
{
    private static final long serialVersionUID = -4134134976863962222L;

	public String getServletInfo()
    {
       return "Servlet connects to PostgreSQL database and displays result of a SELECT";
    }

    private Connection dbcon;  // Connection for scope of this servlet

    // "init" sets up a database connection
    public void init(ServletConfig config) throws ServletException
    {           
        ServletContext context = config.getServletContext();       
        String loginUser = context.getInitParameter("username");
        String loginPasswd = context.getInitParameter("password");
        String loginUrl = context.getInitParameter("dbURL");        

        // Load the PostgreSQL driver
        try 
           {
              Class.forName("org.postgresql.Driver");
              dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              context.setAttribute("db-connection", dbcon);
            }
        catch (ClassNotFoundException ex)
            {
               System.err.println("ClassNotFoundException: " + ex.getMessage());
               throw new ServletException("Class not found Error");
            }
        catch (SQLException ex)
            {
               System.err.println("SQLException: " + ex.getMessage());
            }
    }

    // Use http POST

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
      
        HttpSession session = request.getSession();
        String userID= (String) session.getAttribute("userID");
        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
	    
        try {
          synchronized(dbcon) {
            
            // find max OID
            String query = "SELECT MAX(OID_2) + 1 FROM MESSAGGIOTABLE";
            PreparedStatement statement = dbcon.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            String newOid = "1";
            if (rs.next()) {
             newOid = rs.getString(1);
            }
            rs.close();
            statement.close();
            
                // Declare our statement
                query = "INSERT INTO MESSAGGIOTABLE (TITOLO, CORPO, DATA_ORA, ARGOMENTOOID, USEROID) VALUES (? ,?, ?, ?, ?)";
                statement = dbcon.prepareStatement(query);
                statement.setString(1, (String) request.getParameter("titolo"));
                statement.setString(2, (String) request.getParameter("corpo"));
                statement.setString(3, (String) request.getParameter("data"));
                statement.setInt(4,  new Integer(request.getParameter("argomento")));
                statement.setInt(5, new Integer("101"));
                //statement.setString(6, newOid);
                System.err.println(statement.toString());
                
                // Perform the query
                statement.executeUpdate();                                
                statement.close();
          }
        } catch(Exception ex) {   
                ex.printStackTrace();
                out.println("<HTML>" +
                            "<Head><Title>" +
                            "Db Test: Error" +
                            "</Title></Head>\n<Body>" +
                            "<P>SQL error in doGet: " +
                            ex.getMessage() + "</P></Body></HTML>");
                return;
         }
         
         response.sendRedirect("messaggi.jsp?arg=" + (String) request.getParameter("argomento"));
         //You cannot see the updates because of ACCESS's slowness. Try with another DB
         out.close();
    }
    
    public void destroy() {
      // Clean up
      try {
        if (dbcon != null){
          dbcon.close();
        }
      } catch (SQLException sqle) {}
    }
}
