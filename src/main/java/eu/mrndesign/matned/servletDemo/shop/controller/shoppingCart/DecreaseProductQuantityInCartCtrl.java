package eu.mrndesign.matned.servletDemo.shop.controller.shoppingCart;

import eu.mrndesign.matned.servletDemo.shop.repository.model.noDBModel.ShoppingCart;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DecreaseProductQuantityInCartCtrl", value = "/delete-product-cart")
public class DecreaseProductQuantityInCartCtrl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        String prodItemIdString = req.getParameter("id");
        int prodItemId = Integer.parseInt(prodItemIdString);
        if (validateAmount(cart, prodItemId))
            cart.decreaseQuantity(prodItemId);
        cart.updateTotalPrice();
        resp.sendRedirect("/shopping-cart");
    }

    private boolean validateAmount(ShoppingCart cart, int itemId) {
        int amountInCart = cart.getQuantity(itemId);
        return amountInCart > 0;
    }
}
