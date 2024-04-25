package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.repository.BasketDAOImpl;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
@WebServlet(urlPatterns = {"/RemoveItemFromBasket"})
public class RemoveItemFromBasketServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RemoveItemFromBasketServlet.class);

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
        logger.info(client_id);
        logger.info(Long.toString(product_id), (client_id));
        BasketDAOImpl.getInstance().deleteProductInBasket(client_id,product_id);
        logger.info("success");
    }
}
