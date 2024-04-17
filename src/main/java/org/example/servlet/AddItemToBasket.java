package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketDAOImpl;
import org.example.controllers.ReviewDAOImpl;
import org.example.models.Review;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@WebServlet(name = "AddItemToBasket", urlPatterns = {"/AddItemToBasket"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddItemToBasket extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddItemToBasket.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("doPost");

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.lines().collect(Collectors.joining());
        reader.close();

        // Parse JSON data
        JSONObject jsonObject = new JSONObject(json);

        Long product_id = jsonObject.getLong("product_id");
        String client_id = jsonObject.getString("client_id");
        logger.info(Long.toString(product_id), (client_id));
        BasketDAOImpl.getInstance().addOneProductToBasket((product_id), (client_id));
        logger.info("success");
    }
}
