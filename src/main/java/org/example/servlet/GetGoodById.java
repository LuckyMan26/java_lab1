package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ProductController;
import org.example.repository.ProductDAOImpl;
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
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/GetGoodById"})
public class GetGoodById extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetGoodById.class);
    private Long id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do get");

        logger.info(id);
        Product product =ProductController.INSTANCE.getGoodById(id);
        try (PrintWriter writer = response.getWriter()) {
            Gson gson = new Gson();

            JsonElement element = gson.toJsonTree(product);
            writer.write(element.toString());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.lines().collect(Collectors.joining());
        reader.close();


        JSONObject jsonObject = new JSONObject(json);
        logger.info(jsonObject.toString());

        JSONArray products = jsonObject.getJSONArray("products");
        logger.info(products.length());
        ArrayList<Product> productArrayList = new ArrayList<>();
        logger.info(products);
        for (int i = 0; i < products.length(); i++) {

            Long id = products.getLong(i);



            logger.info(id);
            Product p = ProductController.INSTANCE.getGoodById(id);
            productArrayList.add(p);
        }

        try (PrintWriter writer = response.getWriter()) {
            logger.info("write here");
            Gson gson = new Gson();

            JsonElement element = gson.toJsonTree(productArrayList);
            logger.info(productArrayList.toString());
            writer.write(element.toString());
        }
    }
}
