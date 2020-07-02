package eu.mrndesign.matned.servletDemo.allOtherSht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "ServletDemo",
             value = "/dfg")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        resp.getWriter().println(
                "<html>"+
                "<body><h1>" +
                        "Hello " + name +" "+surname+
                "</h></body></html>");
    }


}
