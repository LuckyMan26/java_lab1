package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.GoodsDAOImpl;
import org.example.models.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
import java.util.List;

@WebServlet(name = "AddGood", urlPatterns = {"/AddGood"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddGoodServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddGoodServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do Post");
       /* ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request.getReader());*/

       /* String name = jsonNode.get("name").asText();
        int price = jsonNode.get("price").asInt();
        String descr = jsonNode.get("description").asText();
        int quantity = jsonNode.get("quantity").asInt();
        GoodsDAOImpl.getInstance().addGood(new Good(1,name,descr,price,quantity));*/
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        for (Part part : request.getParts()) {
            response.getWriter().print(fileName);
            part.write("E:\\temp\\" + fileName);
        }
        //response.getWriter().print("The file uploaded sucessfully.");
        logger.info("Success");

    }
    }

