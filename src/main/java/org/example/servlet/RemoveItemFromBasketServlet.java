package org.example.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketController;
import org.example.dto.ServletJsonMapper;
import org.example.repository.BasketDAOImpl;
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
import java.util.stream.Collectors;
@WebServlet(urlPatterns = {"/RemoveItemFromBasket"})
public class RemoveItemFromBasketServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RemoveItemFromBasketServlet.class);
    private static class Request {
        @JsonProperty("product_id")

        Long product_id;
        @JsonProperty("client_id")

        String client_id;


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        logger.info("doPost");


        Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);

        logger.info(request.client_id);
        logger.info(Long.toString(request.product_id), (request.client_id));
        BasketController.INSTANCE.deleteProductInBasket(request.client_id,request.product_id);
        logger.info("success");
    }
}
