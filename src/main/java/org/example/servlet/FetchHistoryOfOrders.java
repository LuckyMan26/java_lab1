package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/FetchOrders"})
public class FetchHistoryOfOrders  extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FetchHistoryOfOrders.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            logger.info("do get");
        Long client_id = 16L;
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            Gson gson = new Gson();
            ArrayList<Order> listOfOrders = (ArrayList<Order>) OrderDAOImpl.getInstance().getAllOrdersByClient(client_id);
            JsonElement element = gson.toJsonTree(listOfOrders);
            writer.write(element.toString());
        }
        logger.info("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("doPost");


    }
}
