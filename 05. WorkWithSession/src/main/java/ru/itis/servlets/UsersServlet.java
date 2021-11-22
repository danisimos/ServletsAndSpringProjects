package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        userRepository = applicationContext.getBean(UserRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if((Boolean) request.getSession().getAttribute("IsAuthenticated") != null &&
                (Boolean) request.getSession().getAttribute("IsAuthenticated")) {
            List<User> userList = userRepository.findAll();
            request.setAttribute("users", userList);

            request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
        } else {
            response.sendRedirect("/signIn");
        }
    }
}
