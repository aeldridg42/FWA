package edu.school21.cinema.utils;

import jakarta.servlet.http.Part;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Getter
public class FilesUpload {
    private final String PATH;
    private final File DIR;

    @Autowired
    public FilesUpload(String defaultImage) throws IOException {
        PATH = System.getProperty("catalina.home")
                + File.separator + "uploads";
        DIR = new File(PATH);
        if (!DIR.exists()) DIR.mkdir();
        File defIm = new File(defaultImage);
        Files.copy(defIm.toPath(),
                new File(DIR.getAbsoluteFile() + File.separator + defIm.getName()) .toPath(),
                REPLACE_EXISTING);
    }

    public String upload(Part part) throws IOException {
        if (part == null)
            return "";
        String fileName = PATH + File.separator + part.getSubmittedFileName();
        File file = new File(fileName);
        if (file.exists()) {
            fileName = addSuffix(file);
        }
        part.write(fileName);
        return fileName;
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
