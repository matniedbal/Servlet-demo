package eu.mrndesign.matned.servletDemo.shop.controller;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.User;
import eu.mrndesign.matned.servletDemo.shop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {

  private UserService service = UserService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String errorMessage = validateRequest(req);
    if (errorMessage != null) {
      req.setAttribute("error", errorMessage);
      req.getRequestDispatcher("register.jsp").forward(req, resp);
      return;
    }
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    User user = User.builder()
        .username(username)
        .name(name)
        .surname(surname)
        .password(password)
        .build();
    service.save(user);
    resp.sendRedirect("/");
  }

  private String validateRequest(HttpServletRequest req) {
    String name = req.getParameter("name");
    if (name == null || name.isBlank()) {
      return "Invalid user name !";
    }

    String surname = req.getParameter("surname");
    if (surname == null || surname.isBlank()) {
      return "Invalid user surname !";
    }

    String username = req.getParameter("username");
    if (username == null || username.isBlank()) {
      return "Invalid username !";
    }
    boolean exists = service.existsByUsername(username);
    if(exists) {
      return "Username is already in use";
    }

    String password = req.getParameter("password");
    String password2 = req.getParameter("password2");
    if (password == null || password.isBlank() || !password.equals(password2)) {
      return "Invalid user password !";
    }
    return null;
  }
}
