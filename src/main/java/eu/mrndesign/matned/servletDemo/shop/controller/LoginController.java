package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.ShoppingCart;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.User;
import eu.mrndesign.matned.servletDemo.shop.service.ProductService;
import eu.mrndesign.matned.servletDemo.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginController ", value = "/login")
public class LoginController extends HttpServlet {

    private UserService service = UserService.getInstance();
    private ProductService pService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorMessage = validateRequest(req);
        if (errorMessage != null) {
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = service.findUserByLoginAndPassword(login, password);

        if(user.isEmpty()) {
            req.setAttribute("error", "Invalid username or password !");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        User u = user.get();
        pService = ProductService.getInstance();
        HttpSession session = req.getSession();
        session.setAttribute("user", u);
        session.setAttribute("shoppingCart", new ShoppingCart());
        session.setAttribute("currentAllProductsPage", 0);
        session.setAttribute("recordsOnAllProductsPage", 20);
        session.setAttribute("sortByAtribute", "id");
        session.setAttribute("minPrice", 0);
        session.setAttribute("minQuantity", 1);
        session.setAttribute("maxPrice", pService.getMaxPrice());
        session.setAttribute("maxQuantity", pService.getMaxQuantity());
        resp.sendRedirect("/");
    }

    private String validateRequest(HttpServletRequest req) {
        String login = req.getParameter("login");
        if (login == null || login.isBlank()) {
            return "Invalid user login !";
        }

        String password = req.getParameter("password");
        if (password == null || password.isBlank()) {
            return "Invalid user password !";
        }
        return null;
    }
}