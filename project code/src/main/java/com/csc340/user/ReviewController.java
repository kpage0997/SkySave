package com.csc340.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("(create/{username}")
    public Review createReview(@PathVariable String username, @PathVariable Review reviewId, @RequestBody Review review) {
        return reviewService.saveUser(username, reviewId, review);
    }

    @GetMapping("(/all")
    public List<Review> findAllReviews() {
        return reviewService.findAllReviews();
    }

    @GetMapping("/{username}")
    public Optional<Review> findByUsername(@PathVariable String username) {
        return reviewService.findByUsername(username);
    }

    @GetMapping("/{Id}")
    public Optional<Review> findById(@PathVariable ReviewId reviewId) {
        return reviewService.findById(reviewId);
    }

    @DeleteMapping("/delete/{reviewId}")
    public void deleteById(@PathVariable ReviewId reviewId) {
        reviewService.deleteById(reviewId);
    }
}
