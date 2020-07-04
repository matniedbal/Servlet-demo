package eu.mrndesign.matned.servletDemo.shop.controller.shoppingCart;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.ShoppingCart;
import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "IncreaseProductQuantityInCartCtrl", value = "/add-product-cart")
public class IncreaseProductQuantityInCartCtrl extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        String prodItemIdString = req.getParameter("id");
        int prodItemId = Integer.parseInt(prodItemIdString);
        if(validateAmount(cart, prodItemId))
            cart.increaseQuantity(prodItemId);
        cart.updateTotalPrice();
        resp.sendRedirect("/shopping-cart");
    }

    private boolean validateAmount(ShoppingCart cart, int itemId) {
        Product product = cart.getProductById(itemId);
        int amountInCart = cart.getQuantity(itemId);
        int totalAmount = productService.getAbsoluteQuantity(product);
        return amountInCart < totalAmount;
    }
}
