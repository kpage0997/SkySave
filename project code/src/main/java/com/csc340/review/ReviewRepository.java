package com.csc340.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {

    @Query("SELECT r from Review r where r.user.username = :username")
    Optional<Review> findByUsername(@Param("username")String username);

    Optional<Review> findById(ReviewId reviewId);

    int countByFlag(boolean flag);

    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.user.username = :username")
    void deleteByUsername(@Param("username") String username);
}
