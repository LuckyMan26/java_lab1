package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ProductDAOImpl;
import org.example.controllers.ReviewDAOImpl;
import org.example.models.Product;
import org.example.models.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AddReview", urlPatterns = {"/AddReview"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddReview extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddReview.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("here");


        String client_token = request.getParameter("client_token");
        String good_id = request.getParameter("good_id");
        String text = request.getParameter("text");
        String rating = request.getParameter("rating");
        logger.info(text);
        logger.info(good_id);
        logger.info(client_token);
        ReviewDAOImpl.getInstance().addReview(new Review(1L, client_token, Long.parseLong(good_id), text, Integer.parseInt(rating)));
    }
}