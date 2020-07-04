package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList.sort;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchProductController", value = "/search-products")
public class SearchProductController extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String search = req.getParameter("search");
        String searchBy = req.getParameter("search-by");
        String category = req.getParameter("search-by-category");
        String minPrice = req.getParameter("min-price");
        String maxPrice = req.getParameter("max-price");
        String minQuantity = req.getParameter("min-quantity");
        String maxQuantity = req.getParameter("max-quantity");

        productService.search(search, searchBy);
        try {
            productService.setNumberCriteria(
                    Integer.parseInt(minPrice),
                    Integer.parseInt(maxPrice),
                    Integer.parseInt(minQuantity),
                    Integer.parseInt(maxQuantity));
        }catch (NumberFormatException e){
            //here comes an error message
        }
        productService.setCategories(category);
        resp.sendRedirect("/all-products");
    }
}
