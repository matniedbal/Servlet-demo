package eu.mrndesign.matned.servletDemo.allOtherSht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PersonTheSecond", value = "/person2")
public class PersonTheSecond extends HttpServlet {
    String name;
    String beerType;
    String beer;

    PersonTheSecond(String name, String beerType, String beer) {
        this.name = name;
        this.beerType = beerType;
        this.beer = beer;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String beerType = req.getParameter("beertype");
        PersonTheSecond person = null;


        if (beerType.equalsIgnoreCase("strong")) {
            person = new PersonTheSecond(username, beerType, "Warka Strong");
        } else if (beerType.equalsIgnoreCase("dark")) {
            person = new PersonTheSecond(username, beerType, "Lech");
        } else {
            person = new PersonTheSecond(username, beerType, "Desperados");
        }

        req.setAttribute("person2", person);

        req.setAttribute("username", username);
        req.setAttribute("beertype", beerType);

        req.getRequestDispatcher("beerAnswer.jsp").forward(req, resp);
    }


}
