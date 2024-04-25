package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.OrderController;
import org.example.dto.ServletJsonMapper;
import org.example.repository.OrderDAOImpl;
import org.example.models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/GetAllOrders"})
public class GetAllOrdersServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetAllOrdersServlet.class);
    private static class Request {

    }

    private static class Response {
        public List<Order> listOfOrders = new ArrayList<>();

        Response(List<Order> listOfOrders) {
            this.listOfOrders = listOfOrders;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("do get");

        ArrayList<Order> listOfOrders = (ArrayList<Order>) OrderController.INSTANCE.getAllOrders();
        ServletJsonMapper.objectToJsonResponse(new Response(listOfOrders), resp);

        logger.info("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
