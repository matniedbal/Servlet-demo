package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProductController", value = "/edit-product")
public class EditProductController extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product p = productService.findById(Integer.parseInt(id));
        req.setAttribute("id", p.getId());
        req.setAttribute("name", p.getName());
        req.setAttribute("price",  String.valueOf(p.getPrice()));
        req.setAttribute("description",  p.getDescription() );
        req.setAttribute("category",  String.valueOf(p.getCategory()));
        req.setAttribute("quantity",  String.valueOf(p.getQuantity()));
        req.getRequestDispatcher("edit-product.jsp").forward(req,resp);
    }

}