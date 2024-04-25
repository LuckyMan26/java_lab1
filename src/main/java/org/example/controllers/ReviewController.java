package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConnectionPool;
import org.example.connections.ConnectionWrapper;
import org.example.models.Review;
import org.example.repository.ProductDAOImpl;
import org.example.repository.ReviewDAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewController {
    private static final Logger logger = LogManager.getLogger(ReviewController.class);
    public static final ReviewController INSTANCE = new ReviewController();
    public void addReview(Review element ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            ReviewDAOImpl.getInstance().addReview(element, connection);

        }
    }

    public Review getReviewId(int id ) {

        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return  ReviewDAOImpl.getInstance().getReviewId(id, connection);

        }
    }

    public List< Review > getAllReviewsById(int id ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return ReviewDAOImpl.getInstance().getAllReviewsById(id, connection);

        }
    }

    public void deleteReview(int id ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            ReviewDAOImpl.getInstance().deleteReview(id, connection);

        }
    }
}
