package com.csc340.review;

import com.csc340.provider.Provider;
import com.csc340.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@IdClass(ReviewId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "username")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "provider", referencedColumnName = "name")
    private Provider provider;

    private int rating;

    private String comment;

    private boolean flag;
}
