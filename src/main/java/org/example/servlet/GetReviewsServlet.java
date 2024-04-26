package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ReviewController;
import org.example.dto.ServletJsonMapper;
import org.example.models.Product;
import org.example.repository.ReviewDAOImpl;
import org.example.models.Review;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(name = "GetReviews", urlPatterns = {"/GetReviews"})
public class GetReviewsServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(GetReviewsServlet.class);
    private static class Request {
        public int good_id;

    }

    private static class Response {
        public List<Review> listOfReviews = new ArrayList<>();

        Response(List<Review> listOfReviews) {
            this.listOfReviews = listOfReviews;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            try {

                logger.info("do get");


                logger.info(req);
                Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);
                logger.info(request.good_id);

                ArrayList<Review> listOfReviews = (ArrayList<Review>) ReviewController.INSTANCE.getAllReviewsById(request.good_id);
                logger.info(listOfReviews);
                ServletJsonMapper.objectToJsonResponse(new Response(listOfReviews), resp);
            }
            catch (RuntimeException e){
                logger.error(e.getMessage());
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

    }

}