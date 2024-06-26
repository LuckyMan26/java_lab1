package org.example.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketController;
import org.example.controllers.ProductController;
import org.example.dto.ServletJsonMapper;
import org.example.repository.BasketDAOImpl;
import org.example.repository.ProductDAOImpl;
import org.example.models.BasketItem;
import org.example.models.Product;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/FetchBasket"})
public class FetchBasket extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FetchBasket.class);
    private static class Request {
        @JsonProperty("userId")
        public String userId;


    }

    private static class Response {
        public  ArrayList<Product> productsInBasket = new ArrayList<>();
        Response(ArrayList<Product> productsInBasket) {
            this.productsInBasket = productsInBasket;
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {


            logger.info("do post");
            Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);
            logger.info(request.userId);
            BasketItem basketItem = BasketController.INSTANCE.getBasketItemByClientId(request.userId);

            if (basketItem == null) {
                logger.info("success");
            } else {
                ArrayList<Long> products = basketItem.getItems();
                logger.info(products.size());
                ArrayList<Product> productsInBasket = new ArrayList<>();
                for (Long index : products) {
                    logger.info("index: " + index);
                    productsInBasket.add(ProductController.INSTANCE.getGoodById(index));
                }
                logger.info("product size: " + productsInBasket.size());
                ServletJsonMapper.objectToJsonResponse(new Response(productsInBasket), resp);

            }

        }
        catch (RuntimeException e){
            logger.error(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        logger.info("success");
    }
}
