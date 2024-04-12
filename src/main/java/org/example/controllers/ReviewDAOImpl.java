package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DAOInterface.ReviewDAO;
import org.example.connections.ConenctionPool;
import org.example.connections.TransactionWrapper;
import org.example.models.Product;
import org.example.models.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    private static final Logger logger = LogManager.getLogger(ReviewDAOImpl.class);

    private static ReviewDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private ReviewDAOImpl() {
    }

    // Static method to get the singleton instance
    public static synchronized ReviewDAOImpl getInstance() {
        if (instance == null) {
            instance = new ReviewDAOImpl();
        }
        return instance;
    }
    @Override
    public void addReview(Review element) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO reviews (client_id,good_id, text, rating) VALUES ( ?, ?,?,?)");
                statement.setLong(1, element.getClientid());
                statement.setLong(2, element.getGoodId());
                statement.setString(3, element.getText());
                statement.setInt(4, element.getStars());
                statement.executeUpdate();
                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public Review getReviewId(int id) {

        Review g = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            g = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM reviews WHERE review_id = ?");
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                Review e = null;
                while (resultSet.next()) {
                    Long reviewid = resultSet.getLong("blacklist_id");
                    Long clientId = resultSet.getLong("client_id");
                    Long goodId = resultSet.getLong("good_id");
                    String text = resultSet.getString("text");
                    int stars = resultSet.getInt("rating");

                    e = new Review(reviewid, clientId, goodId, text, stars);
                    logger.info(e.toString());
                }
                return e;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Review> getAllReviewsById(int id) {
        List<Review> listOfElements = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            listOfElements = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM reviews WHERE good_id = ?");
                logger.info(id);
                statement.setInt(1,id);

                ResultSet resultSet = statement.executeQuery();
                Review e = null;
                List<Review> list = new ArrayList<Review>();
                while (resultSet.next()) {
                    Long review_id = resultSet.getLong("review_id");
                    Long clientid = resultSet.getLong("client_id");
                    Long good_id = resultSet.getLong("good_id");
                    String text = resultSet.getString("text");
                    int stars = resultSet.getInt("rating");

                    e = new Review(review_id, clientid,good_id, text, stars);
                    logger.info(e.toString());
                    list.add(e);
                }
                return list;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return listOfElements;
    }

    @Override
    public void deleteReview(int id) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("DELETE  * FROM blacklist WHERE review_id = ?");

                statement.setInt(1, id);
                statement.executeUpdate();

                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }
}
