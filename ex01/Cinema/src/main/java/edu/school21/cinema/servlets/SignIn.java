package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/signIn")
public class SignIn extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserServiceImpl.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/signIn.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (userService.signIn(email, password)) {
            request.getSession().setAttribute("user", userService.getUser(email));
            request.getSession().setAttribute("auth", 1);
            response.sendRedirect("/profile");
        } else {
            request.getRequestDispatcher("WEB-INF/jsp/signIn.jsp").forward(request, response);
        }
    }
}
