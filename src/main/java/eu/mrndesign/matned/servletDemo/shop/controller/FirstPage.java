package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FirstPage", value = "/first-page")
public class FirstPage extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            int maxResults = (int) session.getAttribute("recordsOnAllProductsPage");
            session.setAttribute("currentAllProductsPage", 0);
            productService.setPage(0,maxResults);
        }catch (NumberFormatException ignored){
        }finally {
            resp.sendRedirect("/all-products");
        }
    }

}