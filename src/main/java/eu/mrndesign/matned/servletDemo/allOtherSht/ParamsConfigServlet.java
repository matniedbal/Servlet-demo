package eu.mrndesign.matned.servletDemo.allOtherSht;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ParamsConfigServlet",
            value = "/config-params",
            loadOnStartup = 1,
            initParams = {
                 @WebInitParam(name = "city", value = "London"),
                 @WebInitParam(name = "times", value = "5")
    })
public class ParamsConfigServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        String city = servletConfig.getInitParameter("city");
        String times = servletConfig.getInitParameter("times");
        resp.getWriter().println("I have visited "+city+" "+times+" times");
    }
}
