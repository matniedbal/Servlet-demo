package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList.sort;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LastPage", value = "/last-page")
public class LastPage extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            HttpSession session = req.getSession();
            int maxResults = (int) session.getAttribute("recordsOnAllProductsPage");
            int lastPage = productService.getLastPage(maxResults);
            session.setAttribute("currentAllProductsPage", lastPage);
            productService.setPage(lastPage,maxResults);
        }catch (NumberFormatException ignored){
        }finally {
            resp.sendRedirect("/all-products");
        }
    }

}
