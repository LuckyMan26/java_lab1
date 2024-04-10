package org.example.DAOInterface;

import org.example.models.Review;

import java.util.List;

public interface ReviewDAO {
    void addReview(Review good);
    Review getReviewId(int id);
    List<Review> getAllReviewsById(int id);
    void deleteReview(int id);
}
