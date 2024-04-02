package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ProductDAOImpl;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do Post");
       /* ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request.getReader());*/

        String itemName = request.getParameter("name");
        String itemPrice = request.getParameter("price");
        String itemQuantity = request.getParameter("quantity");
        String itemDescription = request.getParameter("description");

        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        ProductDAOImpl.getInstance().addGood(new Product(1,itemName,itemDescription,Integer.parseInt(itemPrice) ,Integer.parseInt(itemQuantity), convertPartToByteArray(filePart)));
        logger.info("Success");

    }
}

