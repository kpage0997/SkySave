package com.csc340.review;

import com.csc340.review.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {

    @Query("SELECT r from Review r where r.user.username = :username")
    Optional<Review> findByUsername(@Param("username")String username);

    Optional<Review> findById(ReviewId reviewId);

}