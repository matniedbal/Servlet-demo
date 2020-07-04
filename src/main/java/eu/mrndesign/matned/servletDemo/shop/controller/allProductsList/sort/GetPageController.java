package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList.sort;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetPageController", value = "/get-page")
public class GetPageController extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int changedPage = Integer.parseInt(req.getParameter("page"));
            int maxResults = Integer.parseInt(req.getParameter("max-per-page"));
            HttpSession session = req.getSession();
            session.setAttribute("currentAllProductsPage", changedPage);
            session.setAttribute("recordsOnAllProductsPage", maxResults);
            productService.setPage(changedPage,maxResults);
            productService.findAll();
        }catch (NumberFormatException ignored){
        }finally {

            resp.sendRedirect("/all-products");
        }
    }
}
