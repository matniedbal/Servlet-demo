package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchProductController", value = "/search-products")
public class SearchProductController extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        String searchBy = req.getParameter("search-by");
        System.out.println("Search item = "+ search+", search by: "+searchBy);

        productService.search(search, searchBy);
        resp.sendRedirect("/all-products");
    }
}
