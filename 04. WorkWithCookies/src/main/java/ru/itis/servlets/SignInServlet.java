package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.filters.SignInFilter;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        userRepository = applicationContext.getBean(UserRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = "Please enter login and password";
        req.setAttribute("message", message);
        req.getRequestDispatcher("/jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String message = "";

        Optional<User> user = userRepository.findByLogin(login);

        if(user.isEmpty()) {
            message = "No such login";
            req.setAttribute("message", message);
            req.getRequestDispatcher("jsp/signIn.jsp").forward(req, resp);
        } else if(!user.get().getPassword().equals(password)) {
            message = "Wrong password";
            req.setAttribute("message", message);
            req.getRequestDispatcher("jsp/signIn.jsp").forward(req, resp);
        } else {
            Cookie cookie = new Cookie(SignInFilter.SESSION_COOKIE_USER_NAME, SignInFilter.SESSION_COOKIE_USER_VALUE);
            resp.addCookie(cookie);
            resp.sendRedirect("/profile");
        }
    }
}
