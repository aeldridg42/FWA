package edu.school21.cinema.utils;

import jakarta.servlet.http.Part;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FilesUpload {
    private static final String PATH;
    private static final File DIR;

    static {
        PATH = System.getProperty("catalina.home")
                + File.separator + "uploads";
        DIR = new File(PATH);
        if (!DIR.exists()) DIR.mkdir();
    }

    public void upload(Part part) throws IOException {
        System.out.println("start");
        if (part == null)
            return;
        System.out.println(1);
        String fileName = PATH + File.separator + part.getSubmittedFileName();
        File file = new File(fileName);
        System.out.println(2);
        if (file.exists()) {
            System.out.println(3);
            fileName = addSuffix(file);
        }
        System.out.println(fileName);
        part.write(fileName);
        System.out.println("end");
    }

    public List<List<String>> getFiles() throws IOException {
        List<List<String>> result = new ArrayList<>();

        for (File file : Objects.requireNonNull(DIR.listFiles())) {
            String MIME = Files.probeContentType(file.toPath());
            if (!MIME.startsWith("image"))
                continue;
            List<String> tmp = new ArrayList<>();
            tmp.add(file.getName());
            tmp.add(formatFileSize(file.length()));
            tmp.add(MIME);
            result.add(tmp);
        }

        return result;
    }

    private String formatFileSize(long size) {
        double k = size / 1024.0;
        double m = ((size / 1000.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0");

        return (m > 1) ? dec.format(m).concat(" MB") :
                (k > 1) ? dec.format(k).concat(" KB")
                        : dec.format(size).concat(" Bytes");
    }

    private String addSuffix(File file) throws IOException {
        String res = file.getName();
        String MIME = Files.probeContentType(file.toPath());
        MIME = "." + MIME.substring(MIME.indexOf("/") + 1);
        if (res.endsWith(MIME))
            res = res.substring(0, res.indexOf(MIME));
        int i = 1;
        String suffix = "(" + i + ")";
        while (new File(PATH + File.separator + res + suffix + MIME).exists())
            suffix = "(" + ++i + ")";
        return PATH + File.separator + res + suffix + MIME;
    }
}
