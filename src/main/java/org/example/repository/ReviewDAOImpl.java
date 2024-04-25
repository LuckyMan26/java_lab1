package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DAOInterface.ReviewDAO;
import org.example.connections.ConenctionPool;
import org.example.connections.ConnectionWrapper;
import org.example.models.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    private static final Logger logger = LogManager.getLogger(ReviewDAOImpl.class);

    private static ReviewDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private ReviewDAOImpl() {}

    // Static method to get the singleton instance
    public static synchronized ReviewDAOImpl getInstance() {
        if (instance == null) {
            instance = new ReviewDAOImpl();
        }
        return instance;
    }

    @Override
    public void addReview(Review element, ConnectionWrapper connection) {
        try {
            String sql = "INSERT INTO reviews (product_id, text, rating,client_id) VALUES ( ?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(4, element.getClientid());
            statement.setLong(1, element.getGoodId());
            statement.setString(2, element.getText());
            statement.setInt(3, element.getStars());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Review getReviewId(int id, ConnectionWrapper connection) {

        Review g = null;
        try {
            String sql = "SELECT * FROM reviews WHERE review_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            Review e = null;
            while (resultSet.next()) {
                Long reviewid = resultSet.getLong("blacklist_id");
                String client_token = resultSet.getString("client_id");
                Long goodId = resultSet.getLong("product_id");
                String text = resultSet.getString("text");
                int stars = resultSet.getInt("rating");

                e = new Review(reviewid, client_token, goodId, text, stars);
                logger.info(e.toString());
            }
            return e;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List < Review > getAllReviewsById(int id, ConnectionWrapper connection) {
        List < Review > listOfElements = null;
        try {
            String sql = "SELECT * FROM reviews WHERE product_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            logger.info(id);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            Review e = null;
            List < Review > list = new ArrayList < Review > ();
            while (resultSet.next()) {
                Long review_id = resultSet.getLong("review_id");
                String client_token = resultSet.getString("client_id");
                Long good_id = resultSet.getLong("product_id");
                String text = resultSet.getString("text");
                int stars = resultSet.getInt("rating");

                e = new Review(review_id, client_token, good_id, text, stars);
                logger.info(e.toString());
                list.add(e);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteReview(int id, ConnectionWrapper connection) {
        try {
            String sql = "DELETE  * FROM blacklist WHERE review_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}