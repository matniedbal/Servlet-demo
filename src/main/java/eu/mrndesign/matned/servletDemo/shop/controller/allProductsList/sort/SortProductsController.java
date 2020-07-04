package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList.sort;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SortProductsController", value = "/sort-products")
public class SortProductsController extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sortBy = req.getParameter("sorting");
        HttpSession session = req.getSession();
        session.setAttribute("sortByAtribute", sortBy);
        productService.sort(sortBy);
        resp.sendRedirect("/all-products");
    }
}
