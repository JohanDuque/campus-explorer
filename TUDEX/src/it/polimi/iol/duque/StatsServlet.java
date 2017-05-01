package it.polimi.iol.duque;

import com.google.gson.Gson;
import it.polimi.iol.duque.model.Users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Duque on 01/05/2017.
 */
public class StatsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        List<Users> usersList = DBManager.getAllUsers();
        String usersJson = new Gson().toJson(usersList); // Here I'm using Gson, one of the most popular JSON serializing libraries

        System.out.println(usersJson);

        response.getWriter().print(usersJson);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBManager.initConnection(config);//Init database connection
        super.init(config);
    }
}
