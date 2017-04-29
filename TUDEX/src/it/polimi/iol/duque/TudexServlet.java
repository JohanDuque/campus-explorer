package it.polimi.iol.duque;


import com.google.gson.Gson;
import it.polimi.iol.duque.bean.Events;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TudexServlet extends HttpServlet {


    private static final long serialVersionUID = -8484923685407220035L;

    private Connection dbcon;  // Connection for scope of this servlet

    // "init" sets up a database connection
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        String loginUser = context.getInitParameter("username");
        String loginPasswd = context.getInitParameter("password");
        String loginUrl = context.getInitParameter("dbURL");

        // Load the PostgreSQL driver
        try {
            Class.forName("org.postgresql.Driver");
            dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
            context.setAttribute("db-connection", dbcon);
        } catch (ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException: " + ex.getMessage());
            throw new ServletException("Class not found Error");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();


        List<Events> events = getAllEventsFromDB();

        String eventsJson = new Gson().toJson(events);
        System.out.println(eventsJson);

        response.setContentType("application/json");
        out.print(eventsJson);

    }

    private List<Events> getAllEventsFromDB() {

        List<Events> events = new ArrayList();

        try {
            String query = "SELECT * FROM events";
            PreparedStatement statement = dbcon.prepareStatement(query);
            try (ResultSet rs = statement.executeQuery()) {

                System.out.println("    **********************    ");
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {

                    Events event = new Events();
                    event.setType(rs.getString("type"));
                    event.setId(rs.getInt("id"));
                    event.setTitle(rs.getString("title"));
                    event.setDescription(rs.getString("description"));
                    event.setFlames(rs.getInt("flames"));
                    event.setLatitude(rs.getDouble("latitude"));
                    event.setLongitude(rs.getDouble("longitude"));
                    event.setStartTime(rs.getLong("starttime"));
                    event.setEndTime(rs.getLong("endtime"));

                    events.add(event);
                  /*  String eventsJson = new Gson().toJson(event);
                    System.out.println(eventsJson);

                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        //System.out.print(columnValue + " " + rsmd.getColumnName(i));
                        System.out.print(i + " " + rsmd.getColumnTypeName(i) + " " + rsmd.getColumnName(i) + " " + rsmd.getColumnLabel(i));
                    }
                    System.out.println("");*/
                }
                rs.close();
            }
            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            String errMsg = "An error occurred while getting events: " + ex.getMessage();
            System.err.println(errMsg);
        }
        return events;
    }

    public String getServletInfo() {
        return "Servlet connects to PostgreSQL database and returns result of a SELECT (in this case a list of events)";
    }



 /*   public static String resultSetToJson(Connection connection, String query) {
        List<Map<String, Object>> listOfMaps = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            listOfMaps = queryRunner.query(connection, query, new MapListHandler());
        } catch (SQLException se) {
            throw new RuntimeException("Couldn't query the database.", se);
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return new Gson().toJson(listOfMaps);
    }*/
}
