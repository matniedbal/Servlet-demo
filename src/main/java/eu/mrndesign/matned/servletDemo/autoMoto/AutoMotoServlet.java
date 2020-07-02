package eu.mrndesign.matned.servletDemo.autoMoto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AutoMotoServlet", value = "/automoto")
public class AutoMotoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("AutoMoto.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownername = req.getParameter("ownername");
        String cartype = req.getParameter("cartype");
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String mileage = req.getParameter("mileage");
        String color = req.getParameter("color");
        String horsepower = req.getParameter("horsepower");

        AutoMoto autoMoto = null;

        if(autoMoto.isNumeric(mileage.trim())) mileage = "Wrong number format";
        if(autoMoto.isNumeric(horsepower.trim())) horsepower = "Wrong number format";

        autoMoto = AutoMoto.builder()
                .ownername(ownername.trim())
                .cartype(cartype.trim())
                .brand(brand.trim())
                .model(model.trim())
                .mileage(mileage.trim())
                .color(color.trim())
                .horsepower(horsepower.trim())
                .build();


        req.setAttribute("automoto", autoMoto);

        req.setAttribute("ownername", ownername);
        req.setAttribute("cartype", cartype);
        req.setAttribute("brand", brand);
        req.setAttribute("model", model);
        req.setAttribute("mileage", mileage);
        req.setAttribute("color", color);
        req.setAttribute("horsepower", horsepower);

        req.getRequestDispatcher("AutoMotoAnswer.jsp").forward(req, resp);
    }

}
