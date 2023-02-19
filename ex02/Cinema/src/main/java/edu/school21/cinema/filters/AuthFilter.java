package edu.school21.cinema.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/signIn", "/signUp"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Object auth = null;
        if (httpServletRequest.getSession(false) != null)
            auth = httpServletRequest.getSession(false).getAttribute("auth");
        String uri = httpServletRequest.getRequestURI();
        if (auth == null && uri.equals("/")) {
            httpServletResponse.sendRedirect("/signIn");
        } else if (auth != null && (uri.equals("/signIn") || uri.equals("/signUp") || uri.equals("/"))) {
            httpServletResponse.sendRedirect("/profile");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
