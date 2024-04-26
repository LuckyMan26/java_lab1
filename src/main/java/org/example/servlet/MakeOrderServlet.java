package org.example.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketController;
import org.example.controllers.OrderController;
import org.example.dto.ServletJsonMapper;
import org.example.models.Product;
import org.example.repository.BasketDAOImpl;
import org.example.repository.OrderDAOImpl;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
@WebServlet(urlPatterns = {"/MakeOrder"})
public class MakeOrderServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MakeOrderServlet.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static class Request {
        @JsonProperty("client_id")
        String client_id;
        @JsonProperty("date")
        String dateString;
        @JsonProperty("address")
        String address;
        @JsonProperty("full_name")
        String full_name;
        @JsonProperty("products")
        List<Long> products;

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {


            logger.info("doPost");

            Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);

            List<Long> products = request.products;

            logger.info(request.address);
            logger.info(request.full_name);
            logger.info(request.dateString);
            Date date;
            try {
                date = dateFormat.parse(request.dateString);
            } catch (ParseException e) {
                logger.info(e.getMessage());
                throw new RuntimeException(e);

            }
            logger.info(date.toString());
            OrderController.INSTANCE.addOrder(new Order(1L, request.client_id, date, (ArrayList<Long>) products, request.full_name, request.address));
            BasketController.INSTANCE.clearBasket(request.client_id);
        }
        catch (RuntimeException e){
            logger.error(e.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        logger.info("success");
    }
}
