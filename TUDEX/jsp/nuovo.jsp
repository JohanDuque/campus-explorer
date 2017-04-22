<%@ page import="java.sql.*,java.util.*,java.text.*" %>


<html>
  <body>
      <form method="post" action="CreaMes">
      <input name="argomento" type="hidden" value="<%=(String) request.getParameter("arg")%>"/>
                    <table>
                    
                      <tr>
                        <td>Data</td>
                        <td>
                          <input readonly name="data" value="<%
                          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                          String datenewformat = formatter.format(new java.util.Date());
                          out.write(datenewformat);
                          %>"/>
                        </td>
                      </tr>
                      <tr>
                        <td>Titolo</td>
                        <td>
                          <input name="titolo">
                        </td>
                      </tr>
                      <tr>
                        <td>Corpo</td>
                        <td>
                          <textarea name="corpo" cols="15" rows="10"></textarea>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2"><input type="submit" value="invia"></td>
                      </tr>
                    </table>
       </form>
  </body>
</html>

