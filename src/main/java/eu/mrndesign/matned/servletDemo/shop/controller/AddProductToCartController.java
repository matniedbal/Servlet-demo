package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.ShoppingCart;
import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name ="AddProductToCartController", value="/add-to-cart")
public class AddProductToCartController extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Integer productId = 0;

        try {
            productId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid product ID: " + productId);
            resp.sendRedirect(("/"));
            return;
        }
        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        Optional<Product> productById = Optional.ofNullable(productService.findById(productId));
        if (productById.isPresent()) {
            shoppingCart.addToShoppingCart(productById.get(),1);
        }
        resp.sendRedirect("/all-products");
    }
}