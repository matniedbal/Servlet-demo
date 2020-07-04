package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.User;
import eu.mrndesign.matned.servletDemo.shop.service.UserService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", value = "/users")
public class UserController extends HttpServlet {

  private final UserService service = UserService.getInstance();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<User> users = service.findAll();
    req.setAttribute("users", users);
    req.getRequestDispatcher("users.jsp").forward(req, resp);
  }
}
