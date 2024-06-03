package com.csc340.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }


    public Optional<Review> findById(ReviewId reviewId) {
        return reviewRepository.findById(reviewId);
    }

    public void deleteByUsername(String username) {
        Review review = reviewRepository.findByUsername(username).orElseThrow(null);
        reviewRepository.delete(review);
    }


    public void saveUser(Review review) {
         reviewRepository.save(review);
    }

    public void deleteById(ReviewId reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public void save(Review review) {
        reviewRepository.save(review);
    }

}