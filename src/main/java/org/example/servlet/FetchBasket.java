package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketDAOImpl;
import org.example.controllers.ProductDAOImpl;
import org.example.models.BasketItem;
import org.example.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
@WebServlet(urlPatterns = {"/FetchBasket"})
public class FetchBasket extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FetchBasket.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do get");
        Long client_id = 16L;
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {

            Gson gson = new Gson();
            BasketItem basketItem = BasketDAOImpl.getInstance().getBasketItemByClientId(client_id);

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
                    prouducts_in_basket.add(ProductDAOImpl.getInstance().getGoodById(index));
                }
                logger.info("product size: " + prouducts_in_basket.size());


                JsonElement element = gson.toJsonTree(prouducts_in_basket);
                writer.write(element.toString());
            }
        }
        logger.info("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do post");
    }
}
