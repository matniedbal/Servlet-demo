package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList;

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
import java.util.*;

@WebServlet(name = "AllProductsController", value = "/all-products")
public class AllProductsController extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        productService.actualizeProductList_WithProductsInShoppingCart(cart);
        req.setAttribute("products", products);
        req.getRequestDispatcher("all-products.jsp").forward(req,resp);
    }

}
