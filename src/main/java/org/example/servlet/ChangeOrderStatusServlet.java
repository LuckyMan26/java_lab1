package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.OrderDAOImpl;
import org.example.models.Order;
import org.example.models.Status;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
@WebServlet(urlPatterns = {"/ChangeOrderStatus"})
public class ChangeOrderStatusServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ChangeOrderStatusServlet.class);

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

        Long order_id = jsonObject.getLong("order_id");
        logger.info(jsonObject.getString("status"));
        Status new_status = Status.valueOf(jsonObject.getString("status"));

        OrderDAOImpl.getInstance().changeOrderStatus(order_id,new_status);

        logger.info("success");
    }
}
