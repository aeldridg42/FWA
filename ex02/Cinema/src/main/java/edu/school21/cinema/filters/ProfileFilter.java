package edu.school21.cinema.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/profile")
public class ProfileFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (httpServletRequest.getSession(false) == null || httpServletRequest.getSession(false).getAttribute("auth") == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
