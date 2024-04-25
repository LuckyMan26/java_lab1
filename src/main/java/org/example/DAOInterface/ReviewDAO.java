package org.example.DAOInterface;

import org.example.connections.ConnectionWrapper;
import org.example.models.Review;

import java.sql.Connection;
import java.util.List;

public interface ReviewDAO {
    public void addReview(Review good, ConnectionWrapper connection);
    public Review getReviewId(int id, ConnectionWrapper connection);
    public List<Review> getAllReviewsById(int id, ConnectionWrapper connection);
    public void deleteReview(int id, ConnectionWrapper connection);
}
