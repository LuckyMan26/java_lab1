package org.example.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ProductDAOImpl;
import org.example.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetGoodById extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetGoodById.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request.getReader());

        int id = jsonNode.get("good_id").asInt();
        Product product = ProductDAOImpl.getInstance().getGoodById(id);
        try (PrintWriter writer = response.getWriter()) {
            Gson gson = new Gson();

            JsonElement element = gson.toJsonTree(product);
            writer.write(element.toString());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



    }
}
