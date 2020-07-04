package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList.sort;

import eu.mrndesign.matned.servletDemo.shop.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "NextPage", value = "/next-page")
public class NextPage extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int currentPage = (int) session.getAttribute("currentAllProductsPage");
        int maxResults = (int) session.getAttribute("recordsOnAllProductsPage");
        int lastPage = productService.getLastPage(maxResults);
        if(currentPage < lastPage) currentPage += 1;
        else currentPage = lastPage;
        session.setAttribute("currentAllProductsPage", currentPage);
        productService.setPage(currentPage,maxResults);
        resp.sendRedirect("/all-products");

    }

}
