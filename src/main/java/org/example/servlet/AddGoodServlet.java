package org.example.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ProductController;
import org.example.dto.ServletJsonMapper;
import org.example.repository.ProductDAOImpl;
import org.example.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddGood", urlPatterns = {"/AddGood"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddGoodServlet extends HttpServlet {
    public static String trimImagePrefix(String imageData) {
        // Define a regular expression pattern to match "data:image/[image_type];base64,"
        Pattern pattern = Pattern.compile("^data:image/[a-zA-Z]+;base64,");
        Matcher matcher = pattern.matcher(imageData);

        // If the pattern is found, remove it from the string
        if (matcher.find()) {
            return imageData.substring(matcher.end());
        } else {
            // Return the original string if the pattern is not found
            return imageData;
        }
    }
    private static final Logger logger = LogManager.getLogger(AddGoodServlet.class);
    private static class Request {
        @JsonProperty("name")
        public String name;
        @JsonProperty("price")
        public String price;
        @JsonProperty("quantity")
        public String quantity;
        @JsonProperty("description")
        public String description;
        @JsonProperty("file")
        public String base64String;

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        logger.info("do Post");
        logger.info(req.getAttributeNames().toString());
        Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);

       /* String fileName = request.file.getSubmittedFileName();
        String base64String = Base64.getEncoder().encodeToString(convertPartToByteArray(request.file));*/

        logger.info(request.base64String);
        String base64String = trimImagePrefix(request.base64String);
        ProductController.INSTANCE.addGood(new Product(1L,request.name,request.description,Integer.parseInt(request.price) ,Integer.parseInt(request.quantity), base64String));
        logger.info("Success");

    }
}

