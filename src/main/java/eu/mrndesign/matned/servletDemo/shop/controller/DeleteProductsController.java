package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProductsController", value = "/delete-product")
public class DeleteProductsController extends HttpServlet {


    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        int productId = 0;

        try{
            productId = Integer.parseInt(id);
        } catch (NumberFormatException e){
            System.out.println("Invalid product ID: " + productId);
        }

        productService.deleteProduct(productId);
        resp.sendRedirect("/all-products");
    }
}
