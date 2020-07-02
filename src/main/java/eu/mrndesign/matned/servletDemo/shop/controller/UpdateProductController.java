package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Category;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static eu.mrndesign.matned.servletDemo.shop.controller.Validate.validateRequest;


@WebServlet(name = "UpdateProductController", value = "/update-product")
public class UpdateProductController extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        String errorMessage = validateRequest(name,price,description,category,quantity);
        if(errorMessage != null){
            req.setAttribute("error", errorMessage);
            req.setAttribute("id", id);
            req.setAttribute("name", name);
            req.setAttribute("price",  price);
            req.setAttribute("description",  description);
            req.setAttribute("category",  category);
            req.setAttribute("quantity",  quantity);
            req.getRequestDispatcher("edit-product.jsp").forward(req,resp);
            return;
        }
        Product p = productService.findById(Integer.parseInt(id));
        p.setName(name);
        p.setCategory(Category.valueOf(category));
        p.setDescription(description);
        p.setPrice(Integer.parseInt(price));
        p.setQuantity(Integer.parseInt(quantity));
        productService.setProduct(p);
        resp.sendRedirect("/all-products");
    }


}
