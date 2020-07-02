package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SortProductsController", value = "/sort-products")
public class SortProductsController extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sortBy = req.getParameter("sorting");
        productService.sortProducts(sortBy);
        resp.sendRedirect("/all-products");
    }
}
