<%@ page import="java.sql.*,java.util.*,java.text.*" %>
<%
        Connection dbcon = null;
        ResultSet rs = null;
        ServletContext context = config.getServletContext();       
        String loginUser = context.getInitParameter("username");
        String loginPasswd = context.getInitParameter("password");
        String loginUrl = context.getInitParameter("dbURL");
        String arg = request.getParameter("arg");

        try {
        	// Load (and therefore register) the BrdigeODBC/JDBC Driver
	        //ACCESS --> Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	    
		    // Get a Connection to the database
		    //ACCESS --> con = DriverManager.getConnection("jdbc:odbc:TIWIOLJDBC", "", "");
		    // !!! YOU NEED TO CREATE THE ODBC BRIDGE IN YOUR OS
        	
        		  Class.forName("org.postgresql.Driver");
              dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);              
        } catch (ClassNotFoundException ex) {
               System.err.println("ClassNotFoundException: " + ex.getMessage());
               throw new ServletException("Class not found Error");
        } catch (SQLException ex) {
               System.err.println("SQLException: " + ex.getMessage());
        }
        
        try {
                // Declare our statement
                String query = "SELECT * FROM ARGOMENTOTABLE";
                PreparedStatement statement = dbcon.prepareStatement(query);
                //out.write(statement.toString());

                // Perform the query
                statement.execute();
                rs = statement.getResultSet();
                
                
%>
            
<HTML>
  <Head>
    <Title>Indice Argomenti</Title>
  </Head>
  <Body>
  <H1>Indice Argomenti</H1>
    <table>
      <tr>
        <td>
        <table border>
          <tr>
            <td colspan="3">
              <b>Argomenti</b>
            </td>
          </tr>
<%
                // Iterate through each row of rs
                while (rs.next()) {
%>                   
                   <tr><td><a href="messaggi.jsp?arg=<%=rs.getString(1)%>"><%=rs.getString(2)%></a></td>
                   <td><%=rs.getString(3)%></td>
                  
                   </tr>
<%                   
                }
                statement.close();
%>
                      </table>
                    </td>                
                  </tr>
            </table>
        </body>
    </html>
<%
        } catch(Exception ex) {
             out.println( ex.getMessage() );                
        }
            
%>
