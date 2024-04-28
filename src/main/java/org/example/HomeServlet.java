package org.example;

import com.auth0.SessionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/portal/home"})
public class HomeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String accessToken = (String) SessionUtils.get(req, "accessToken");
        final String idToken = (String) SessionUtils.get(req, "idToken");
        logger.info(accessToken);
        logger.info(idToken);
        if (accessToken != null) {
            req.setAttribute("accessToken", accessToken);
        }
        if (idToken != null) {
            req.setAttribute("userId", idToken);
        }
        res.setContentType("text/html");
        String folderPath = "./";

        // Create a Path object for the folder
        Path dir = Paths.get(folderPath);

        try {
            // Get a stream of all files within the directory
            Files.list(dir)
                    .forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder htmlContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/webapp/WEB-INF/jsp/home.html"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line);
            }
        }
        String script = "<script>var userId = '" + idToken + "';</script>";

        // Append the script to the HTML content
        int index = htmlContent.indexOf("</body>");
        if (index != -1) {
            htmlContent.insert(index, script);
        } else {
            // If </body> tag not found, append the script at the end of the HTML content
            htmlContent.append(script);
        }


        //req.getRequestDispatcher("/WEB-INF/jsp/home.html").forward(req, res);
        try (PrintWriter writer = res.getWriter()) {
            writer.write(htmlContent.toString());
        }

    }

}

