package edu.school21.cinema.servlets;

import edu.school21.cinema.utils.FilesUpload;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/profile")
@MultipartConfig
public class Profile extends HttpServlet {
    private FilesUpload filesUpload;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.filesUpload = springContext.getBean(FilesUpload.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/signIn");
        }
        else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
            req.setAttribute("files", filesUpload.getFiles());
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filesUpload.upload(req.getPart("file"));
        doGet(req, resp);
    }
}
