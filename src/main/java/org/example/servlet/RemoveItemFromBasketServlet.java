package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketDAOImpl;
import org.example.controllers.OrderDAOImpl;
import org.example.models.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Long client_id = jsonObject.getLong("client_id");
        logger.info(Long.toString(product_id), Long.toString(client_id));
        BasketDAOImpl.getInstance().deleteProductInBasket(client_id,product_id);
        logger.info("success");
    }
}