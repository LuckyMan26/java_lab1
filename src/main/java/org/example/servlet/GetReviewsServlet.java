package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ReviewController;
import org.example.repository.ReviewDAOImpl;
import org.example.models.Review;
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
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(name = "GetReviews", urlPatterns = {"/GetReviews"})
public class GetReviewsServlet extends HttpServlet {
    private int good_id;
    private static final Logger logger = LogManager.getLogger(GetReviewsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do get");

        response.setContentType("application/json;charset=UTF-8");
        logger.info("fdasfadsfadsf");
        try (PrintWriter writer = response.getWriter()) {

            logger.info(good_id);
            Gson gson = new Gson();
            ArrayList<Review> listOfReviews = (ArrayList<Review>) ReviewController.INSTANCE.getAllReviewsById(good_id);
            logger.info(listOfReviews);
            JsonElement element = gson.toJsonTree(listOfReviews);
            writer.write(element.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do Post");

        // Read JSON data from the request body
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.lines().collect(Collectors.joining());
        reader.close();

        // Parse JSON data
        JSONObject jsonObject = new JSONObject(json);
        String command = jsonObject.getString("command");

        logger.info(command);
        if(Objects.equals(command, "good_id")){
            logger.info("banana");
            logger.info(jsonObject.toString());
            good_id = jsonObject.getInt("good_id");
        }

        logger.info("Success");
    }
}