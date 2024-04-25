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

@WebServlet(name = "AddGood", urlPatterns = {"/AddGood"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddGoodServlet extends HttpServlet {
    private byte[] convertPartToByteArray(Part part) throws IOException {
        // Create a byte array output stream
        byte[] byteArray = null;
        try (InputStream inputStream = part.getInputStream()) {
            // Read the contents of the part into a byte array
            byteArray = inputStream.readAllBytes();
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            throw e;
        }
        return byteArray;
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
        public Part file;

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        logger.info("do Post");

        Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);

        String fileName = request.file.getSubmittedFileName();
        String base64String = Base64.getEncoder().encodeToString(convertPartToByteArray(request.file));

        ProductController.INSTANCE.addGood(new Product(1L,request.name,request.description,Integer.parseInt(request.price) ,Integer.parseInt(request.quantity), base64String));
        logger.info("Success");

    }
}

