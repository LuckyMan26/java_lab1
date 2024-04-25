package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketController;
import org.example.controllers.ProductController;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do post");
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.lines().collect(Collectors.joining());
        reader.close();


        JSONObject jsonObject = new JSONObject(json);


        String client_id = jsonObject.getString("user_id");
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {

            Gson gson = new Gson();
            BasketItem basketItem = BasketController.INSTANCE.getBasketItemByClientId(client_id);

            logger.info(basketItem);
            //logger.info(basketItem.toString());
            if (basketItem == null) {
                logger.info("success");
            } else {
                ArrayList<Long> products = basketItem.getItems();
                logger.info(products.size());
                ArrayList<Product> prouducts_in_basket = new ArrayList<>();
                for (Long index : products) {
                    logger.info("index: " + index);
                    prouducts_in_basket.add(ProductController.INSTANCE.getGoodById(index));
                }
                logger.info("product size: " + prouducts_in_basket.size());


                JsonElement element = gson.toJsonTree(prouducts_in_basket);
                writer.write(element.toString());
            }
        }
        logger.info("success");
    }
}
