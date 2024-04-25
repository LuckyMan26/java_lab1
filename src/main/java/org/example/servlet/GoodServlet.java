package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.repository.ProductDAOImpl;
import org.example.models.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GoodServlet", urlPatterns = {"/GoodServlet"})
public class GoodServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(GoodServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do get");
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            Gson gson = new Gson();
            ArrayList<Product> listOfProducts = (ArrayList<Product>) ProductDAOImpl.getInstance().getAllGoods();
            JsonElement element = gson.toJsonTree(listOfProducts);
            writer.write(element.toString());
        }
        logger.info("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
