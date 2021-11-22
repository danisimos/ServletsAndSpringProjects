package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SignInFilter implements Filter {
    public final static String SESSION_COOKIE_USER_NAME = "isUserSignedIn";
    public final static String SESSION_COOKIE_USER_VALUE = "пользовательАутентифицирован";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        boolean isRequestOnSignInOrSignUp = httpServletRequest.getRequestURI().equals("/signIn") ||
                httpServletRequest.getRequestURI().equals("/signUp");

        boolean isRequestOnProfile = httpServletRequest.getRequestURI().equals("/profile");

        System.out.println(httpServletRequest.getRequestURI());

        if(processCookies(httpServletRequest)) {
            if(isRequestOnProfile) chain.doFilter(httpServletRequest, httpServletResponse);
            else httpServletResponse.sendRedirect("/profile");
        } else {
            if(isRequestOnSignInOrSignUp) chain.doFilter(httpServletRequest, httpServletResponse);
            else httpServletResponse.sendRedirect("/signIn");
        }
    }

    private boolean processCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies) {
            if(cookie.getValue().equals(SESSION_COOKIE_USER_VALUE))
                return true;
        }

        return false;
    }

    @Override
    public void destroy() {

    }
}
