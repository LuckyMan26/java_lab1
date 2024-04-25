package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.OrderController;
import org.example.dto.ServletJsonMapper;
import org.example.models.Product;
import org.example.repository.OrderDAOImpl;
import org.example.models.Order;
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

@WebServlet(urlPatterns = {"/FetchOrders"})
public class FetchHistoryOfOrders  extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FetchHistoryOfOrders.class);

    private static class Request {
        public String user_id;

    }

    private static class Response {
        public  ArrayList<Order> listOfOrders = new ArrayList<>();
        Response(ArrayList<Order> listOfOrders) {
            this.listOfOrders = listOfOrders;
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            logger.info("do get");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        logger.info("doPost");


        Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);


        logger.info("getAllOrdersByClient");
        ArrayList<Order> listOfOrders = (ArrayList<Order>) OrderController.INSTANCE.getAllOrdersByClient(request.user_id);
        logger.info(listOfOrders.toString());
        ServletJsonMapper.objectToJsonResponse(new Response(listOfOrders), res);

        logger.info("success");

    }
}
