package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/")
public class CookieFilter implements Filter {
    public final static String IS_AUTHENTICATED_COOKIE = "IsAuthenticated";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if(processCookies(httpServletRequest)) {
            httpServletRequest.getSession(true).setAttribute("isAuthenticated", true);
            httpServletResponse.sendRedirect("/profile");
        } else {
            httpServletResponse.sendRedirect("/signIn");
        }
    }

    private boolean processCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies) {
            if(cookie.getName().equals(IS_AUTHENTICATED_COOKIE))
                return true;
        }

        return false;
    }

    @Override
    public void destroy() {

    }

}
