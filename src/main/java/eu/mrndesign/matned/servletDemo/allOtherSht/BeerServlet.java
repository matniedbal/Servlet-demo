package eu.mrndesign.matned.servletDemo.allOtherSht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BeerServlet", value = "/beer")
public class BeerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("BeerAdvise.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String beertype = req.getParameter("beertype");

        req.setAttribute("username",username);
        req.setAttribute("beertype",beertype);

        req.getRequestDispatcher("BeerAnswer.jsp").forward(req,resp);
    }
}
