package ru.itis.servlets;

import ru.itis.filters.CookieFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signOut")
public class SignOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie(CookieFilter.IS_AUTHENTICATED_COOKIE, "false");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        request.removeAttribute("user");
        request.getSession().setAttribute("IsAuthenticated", false);

        response.sendRedirect("/signIn");
    }
}
