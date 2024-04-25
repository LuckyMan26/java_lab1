package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@WebServlet(urlPatterns = {"/GetAllOrders"})
public class GetAllOrdersServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetAllOrdersServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do get");
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            Gson gson = new Gson();
            ArrayList<Order> listOfOrders = (ArrayList<Order>) OrderDAOImpl.getInstance().getAllOrders();
            JsonElement element = gson.toJsonTree(listOfOrders);
            writer.write(element.toString());
        }
        logger.info("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
