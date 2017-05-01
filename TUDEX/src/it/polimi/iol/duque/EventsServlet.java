package it.polimi.iol.duque;


import com.google.gson.Gson;
import it.polimi.iol.duque.bean.Events;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Duque on 29/04/2017.
 */
public class EventsServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();//It writes the response

        List<Events> events = DBManager.getAllEvents();
        String eventsJson = new Gson().toJson(events); // Here I'm using Gson, one of the most popular JSON serializing libraries

        System.out.println(eventsJson);

        out.print(eventsJson);
    }

    public String getServletInfo() {
        return "Servlet that connects to PostgreSQL database and returns result of a SELECT (in this case a list of events)";
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        DBManager.initConnection(config);//Init database connection
        super.init(config);
    }

}
