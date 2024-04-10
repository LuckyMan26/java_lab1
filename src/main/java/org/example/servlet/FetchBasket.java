package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketDAOImpl;
import org.example.controllers.ProductDAOImpl;
import org.example.models.BasketItem;
import org.example.models.OrderItem;
import org.example.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "FetchBasket", urlPatterns = {"/FetchBasket"})
public class FetchBasket extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(FetchBasket.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do get");
        int client_id = 16;
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            Gson gson = new Gson();
            BasketItem basketItem =  BasketDAOImpl.getInstance().getBasketItemByClientId(client_id);

            JsonElement element = gson.toJsonTree(basketItem);
            writer.write(element.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
