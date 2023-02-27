package edu.school21.cinema.servlets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/FWA/images/*")
public class Images extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!request.getRequestURL().toString().contains("FWA"))
            return;
        response.setContentType("image/jpeg");
        String url = request.getRequestURL().toString();
        String imageName = url.substring(url.lastIndexOf("/")).replaceAll("%20"," ");

        ServletOutputStream out = response.getOutputStream();
        FileInputStream flinp = null;
        try {
            flinp = new FileInputStream("/home/aeldridg/apache-tomcat-10.0.27/uploads/" + imageName);
        } catch (FileNotFoundException e) {
//            flinp = new FileInputStream(imgStorage + "xxxxxxxxxxxxxx-default.png");
        }
        assert flinp != null;
        BufferedInputStream buffinp = new BufferedInputStream(flinp);
        BufferedOutputStream buffoup = new BufferedOutputStream(out);

        int ch = 0;
        while ((ch = buffinp.read()) != -1)
            buffoup.write(ch);

        buffinp.close();
        flinp.close();
        buffoup.close();
        out.close();
    }

}
