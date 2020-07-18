package eu.mrndesign.matned.servletDemo.shop.controller.shoppingCart;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.repository.model.noDBModel.ShoppingCart;
import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        int productId = Integer.parseInt(id);
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        Optional<Product> productById = Optional.ofNullable(productService.findById(productId));
        cart.getProductItems().stream()
                .filter(p->p.equals(cart.findProductItemByProductId(productId)))
                .findFirst()
                .ifPresentOrElse((p)->{
                    if(validateAmount(cart, cart.findProductItemByProductId(productId).getId()))
                        productById.ifPresent(product -> cart.addToShoppingCart(product, 1));
                }, () -> productById.ifPresent(product -> cart.addToShoppingCart(product, 1)));
        cart.updateTotalPrice();
        resp.sendRedirect("/all-products");
    }

    private boolean validateAmount(ShoppingCart cart, int itemId) {
        Product product = cart.getProductById(itemId);
        int amountInCart = cart.getQuantity(itemId);
        int totalAmount = productService.getAbsoluteQuantity(product);
        return amountInCart < totalAmount;
    }
}