package org.example.DAOInterface;

import org.example.models.Review;

import java.util.List;

public interface ReviewDAO {
    public void addReview(Review good);
    public Review getReviewId(int id);
    public List<Review> getAllReviewsById(int id);
    public void deleteReview(int id);
}
