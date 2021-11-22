package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.SignInForm;
import ru.itis.filters.CookieFilter;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.SignInService;

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
    SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        signInService = applicationContext.getBean(SignInService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignInForm signInForm = SignInForm.builder()
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .build();
        System.out.println(signInForm.getLogin());

        if(signInService.signIn(signInForm)) {
            Cookie cookie = new Cookie(CookieFilter.IS_AUTHENTICATED_COOKIE, "true");
            response.addCookie(cookie);

            request.getSession().setAttribute("IsAuthenticated", true);
            request.getSession().setAttribute("user", signInService.getUser());

            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/signIn?error");
        }
    }
}
