package eu.mrndesign.matned.servletDemo.allOtherSht;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContextParamServlet", value = "/context-params")
public class ContextParamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String email = servletContext.getInitParameter("email");
        resp.getWriter().println(
                "<html>"+
                "<body><h1>" +
                        "email: " + email+
                "</h></body></html>");
    }


}
