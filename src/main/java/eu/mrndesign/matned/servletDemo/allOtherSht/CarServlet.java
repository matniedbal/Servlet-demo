package eu.mrndesign.matned.servletDemo.allOtherSht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CarServlet" , value = "/cars")
public class CarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("country");
        String cars = null;
        if (country == null){
            cars = "no cars";
        }else if(country.equalsIgnoreCase("Niemcy")) {
            cars = "Merol, BMW, Audi";
        }else if(country.equalsIgnoreCase("Francja")) {
            cars = "Renault, Citroen, Peżot";
        }else if(country.equalsIgnoreCase("Hiszpania")) {
            cars = "Seat";
        }else if(country.equalsIgnoreCase("Polska")) {
            cars = "Polonez";
        }
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("cars.jsp").forward(req,resp);
    }
}
