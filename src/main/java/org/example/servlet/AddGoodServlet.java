package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.GoodsDAOImpl;
import org.example.models.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "AddGood", urlPatterns = {"/AddGood"})

public class AddGoodServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GoodServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request.getReader());

        String name = jsonNode.get("name").asText();
        int price = jsonNode.get("price").asInt();
        String descr = jsonNode.get("description").asText();
        int quantity = jsonNode.get("quantity").asInt();
        GoodsDAOImpl.getInstance().addGood(new Good(1,name,descr,price,quantity));
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request.getReader());

        String name = jsonNode.get("name").asText();
        int price = jsonNode.get("price").asInt();
        String descr = jsonNode.get("description").asText();
        int quantity = jsonNode.get("quantity").asInt();
        GoodsDAOImpl.getInstance().addGood(new Good(1,name,descr,price,quantity));

    }
    }

