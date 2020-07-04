package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Category;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static eu.mrndesign.matned.servletDemo.shop.controller.allProductsList.Validate.validateRequest;

@WebServlet(name = "AddProductController", value = "/add-product")
public class AddProductController extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add-product.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        String errorMessage = validateRequest(name,price,description,category,quantity);
        if(errorMessage != null){
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("add-product.jsp").forward(req,resp);
            return;
        }
        Product product = Product.builder()
                .name(name)
                .category(Category.valueOf(category))
                .description(description)
                .price(Integer.valueOf(price))
                .quantity(Integer.valueOf(quantity))
                .build();
        productService.saveProduct(product);
        resp.sendRedirect("/all-products");

    }

}
