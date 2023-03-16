package edu.school21.cinema.servlets;

import edu.school21.cinema.utils.FilesUpload;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.*;

@WebServlet("/FWA/images/*")
public class Images extends HttpServlet {

    private FilesUpload filesUpload;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.filesUpload = springContext.getBean(FilesUpload.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!request.getRequestURL().toString().contains("FWA"))
            return;
        response.setContentType("image/jpeg");
        String url = request.getRequestURL().toString();
        String imageName = url.substring(url.lastIndexOf("/")).replaceAll("%20"," ");

        ServletOutputStream out = response.getOutputStream();
        FileInputStream flinp;
        try {
            flinp = new FileInputStream(filesUpload.getPATH() + File.separator + imageName);
        } catch (FileNotFoundException e) {
            flinp = new FileInputStream(filesUpload.getPATH() + File.separator + "/default.webp");
        }
        BufferedInputStream buffinp = new BufferedInputStream(flinp);
        BufferedOutputStream buffoup = new BufferedOutputStream(out);

        int ch;
        while ((ch = buffinp.read()) != -1)
            buffoup.write(ch);

        buffinp.close();
        flinp.close();
        buffoup.close();
        out.close();
    }

}
