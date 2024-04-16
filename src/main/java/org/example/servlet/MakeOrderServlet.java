package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketDAOImpl;
import org.example.controllers.OrderDAOImpl;
import org.example.controllers.ProductDAOImpl;
import org.example.models.Order;
import org.example.models.Product;
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
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
@WebServlet(urlPatterns = {"/MakeOrder"})
public class MakeOrderServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MakeOrderServlet.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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


        JSONObject jsonObject = new JSONObject(json);

        JSONArray products = jsonObject.getJSONArray("products");
        ArrayList<Long> products_in_order = new ArrayList<>();

        for (int i = 0; i < products.length(); i++) {

            JSONObject product = products.getJSONObject(i);

            Long id = product.getLong("product_id");
            products_in_order.add(id);


            logger.info(id );
        }
        Long client_id = jsonObject.getLong("client_id");
        String dateString = jsonObject.getString("date");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.info(e.getMessage());
            throw new RuntimeException(e);

        }
        logger.info(date.toString());
        OrderDAOImpl.getInstance().addOrder(new Order(1L,client_id,date,products_in_order));
        BasketDAOImpl.getInstance().clearBasket(client_id);
        logger.info("success");
    }
}
