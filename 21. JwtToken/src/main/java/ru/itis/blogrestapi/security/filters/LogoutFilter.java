package ru.itis.blogrestapi.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.blogrestapi.repositories.JwtTokenBlackListRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class LogoutFilter extends OncePerRequestFilter {
    public static final String API_LOGOUT = "/logout/";

    private final JwtTokenBlackListRepository jwtTokenBlackListRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(request.getRequestURI().equals(API_LOGOUT) && authentication != null) {
            String token = (String) authentication.getPrincipal();
            jwtTokenBlackListRepository.save(token);

            SecurityContextHolder.getContext();
        }

        filterChain.doFilter(request, response);
    }
}
