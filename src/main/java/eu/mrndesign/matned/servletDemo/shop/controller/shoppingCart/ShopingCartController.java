package eu.mrndesign.matned.servletDemo.shop.controller.shoppingCart;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ShopingCartController", value = "/shopping-cart")
public class ShopingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        cart.updateTotalPrice();
        req.getRequestDispatcher("shopping-cart.jsp").forward(req,resp);
    }
}
