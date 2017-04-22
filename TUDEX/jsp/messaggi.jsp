<%@ page import="java.sql.*,java.util.*,java.text.*" %>
<%
	Connection dbcon = null;
	ResultSet rs = null;
	ServletContext context = config.getServletContext();       
    String loginUser = context.getInitParameter("username");
    String loginPasswd = context.getInitParameter("password");
    String loginUrl = context.getInitParameter("dbURL");
    
    String arg = request.getParameter("arg");
        
        try 
           {
              Class.forName("org.postgresql.Driver");
              dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);              
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
        // ora i messaggi 
        
        if ((arg != null) && (arg!="")) {            
	        try
	            {
	                // Declare our statement
	                String query = "SELECT NOME FROM ARGOMENTOTABLE WHERE OID_2 = ?";
	                PreparedStatement statement = dbcon.prepareStatement(query);
	                statement.setInt(1, new Integer(arg));
	                //out.write(statement.toString());
	                
	
	                // Perform the query
	                rs = statement.executeQuery();
	                rs.next();

%>
            
<HTML><Head><Title>Messaggi</Title></Head>
<Body><H1>Messaggi relativi a: <%=rs.getString(1)%></H1>
<table>
<tr>
<td>

                
<%                
                
                
%>
                <table border>
<%               
                rs.close();
 
                // Declare a new statement
                
                String mquery = "SELECT A.DATA_ORA, B.USERNAME, A.TITOLO FROM MESSAGGIOTABLE AS A LEFT JOIN USERTABLE AS B ON  A.USEROID =  B.OID_2 WHERE ARGOMENTOOID = ?";
                PreparedStatement mstatement = dbcon.prepareStatement(mquery);
                mstatement.setInt(1, new Integer(arg));
                //out.write(mstatement.toString());
                
                // Perform the query
                ResultSet mrs = mstatement.executeQuery();
                
                 // Iterate through each row of rs
                while (mrs.next())
                {

%>                   
                   <tr>
                       <td><%=mrs.getString(1)%></td>
                       <td><%=mrs.getString(2)%></td>
                       <td><%=mrs.getString(3)%></td>                       
                   </tr>
<%                   
                }
                mstatement.close();
%>
                </table>
</td>
                <td>                
                  <jsp:include page="nuovo.jsp"/>
                </td>
                </tr>
                
                </table></body></html>
<%
            }
        catch(Exception ex)
            {
                out.println( ex.getMessage() );
                
            }
             }
%>
