package it.polimi.iol.duque;

import com.google.gson.Gson;
import it.polimi.iol.duque.model.Users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Duque on 01/05/2017.
 */
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Users user = DBManager.getUser(username, password);
        String userJson = new Gson().toJson(user); // Here I'm using Gson, one of the most popular JSON serializing libraries

        System.out.println(user);
        response.getWriter().print(userJson);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBManager.initConnection(config);//Init database connection
        super.init(config);
    }
}
