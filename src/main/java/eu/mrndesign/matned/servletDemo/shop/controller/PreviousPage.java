package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PreviousPage", value = "/prev-page")
public class PreviousPage extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            int currentPage = (int) session.getAttribute("currentAllProductsPage");
            int maxResults = (int) session.getAttribute("recordsOnAllProductsPage");
            if(currentPage > 0) currentPage -= 1;
            else currentPage = 0;
            session.setAttribute("currentAllProductsPage", currentPage);
            productService.setPage(currentPage,maxResults);
            resp.sendRedirect("/all-products");
    }

}
