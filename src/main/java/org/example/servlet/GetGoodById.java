package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ProductController;
import org.example.dto.ServletJsonMapper;
import org.example.models.Product;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetGoodById extends HttpServlet {
    private static class Request {
        public Long product_id;

    }

    private static class Response {
        public Product product;

        Response(Product product) {
            this.product = product;
        }
    }
    private static final Logger logger = LogManager.getLogger(GetGoodById.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {

            logger.info("do get");
            Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);
            Product product = ProductController.INSTANCE.getGoodById(request.product_id);
            ServletJsonMapper.objectToJsonResponse(new Response(product), resp);
        }
        catch (RuntimeException e){
            logger.error(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
