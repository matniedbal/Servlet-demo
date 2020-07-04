package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProductsController", value = "/delete-product")
public class DeleteProductsController extends HttpServlet {


    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        try {
            productService.deleteProduct(Integer.parseInt(id));
        }catch (NumberFormatException ignored){
        }finally {
            resp.sendRedirect("/all-products");
        }
    }
}
