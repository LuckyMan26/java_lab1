package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ReviewController;
import org.example.dto.ServletJsonMapper;
import org.example.repository.ReviewDAOImpl;
import org.example.models.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddReview", urlPatterns = {"/AddReview"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddReview extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddReview.class);
    private static class Request {
        public String client_token;
        public String good_id;
        public String text;
        public String rating;

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        logger.info("here");
       Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);

        ReviewController.INSTANCE.addReview(new Review(1L, request.client_token, Long.parseLong(request.good_id), request.text, Integer.parseInt(request.rating)));
    }
}