package it.polimi.iol.duque;

import it.polimi.iol.duque.bean.Events;
import it.polimi.iol.duque.bean.Users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duque on 01/05/2017.
 */
public class DBManager {

    private static Connection connection = null;  // Connection to DataBase

    public static void initConnection(ServletConfig config) throws ServletException {
        if (connection == null) {

            ServletContext context = config.getServletContext();
            String loginUser = context.getInitParameter("username");
            String loginPasswd = context.getInitParameter("password");
            String loginUrl = context.getInitParameter("dbURL");

            // Load PostgreSQL driver and sets the connection up
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
                context.setAttribute("db-connection", connection);
            } catch (ClassNotFoundException ex) {
                System.err.println("ClassNotFoundException: " + ex.getMessage());
                throw new ServletException("Class not found Error");
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
        }
    }


    public static List<Events> getAllEvents() {
        List<Events> events = new ArrayList();

        try {
            String query = "SELECT * FROM events";
            PreparedStatement statement = connection.prepareStatement(query);
            try (ResultSet rs = statement.executeQuery()) {
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

    public static List<Users> getAllUsers() {
        List users = new ArrayList<Users>();

        try {
            String query = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {

                    Users user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setSurname(rs.getString("surname"));
                    user.setPoints(rs.getInt("points"));
                    user.setFaculty(rs.getString("faculty"));

                    users.add(user);
                }
                rs.close();
            }
            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            String errMsg = "An error occurred while getting users: " + ex.getMessage();
            System.err.println(errMsg);
        }

        return users;
    }

    public static Users getUser(String username, String password) {
        Users user = new Users();

        try {

            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setSurname(rs.getString("surname"));
                user.setPoints(rs.getInt("points"));
                user.setFaculty(rs.getString("faculty"));

            }
            rs.close();

            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            String errMsg = "An error occurred while getting users: " + ex.getMessage();
            System.err.println(errMsg);
        }

        return user;
    }

}
