package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.OrderController;
import org.example.dto.ServletJsonMapper;
import org.example.repository.OrderDAOImpl;
import org.example.models.Status;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
@WebServlet(urlPatterns = {"/ChangeOrderStatus"})
public class ChangeOrderStatusServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ChangeOrderStatusServlet.class);
    private static class Request {
        public String order_id;
        public String status;


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
            OrderController.INSTANCE.changeOrderStatus(Long.parseLong(request.order_id), Status.valueOf(request.status));

            logger.info("success");
        }
        catch (RuntimeException e){
            logger.error(e.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
