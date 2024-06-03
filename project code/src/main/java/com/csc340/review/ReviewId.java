package com.csc340.review;

import java.io.Serializable;

import com.csc340.provider.Provider;
import com.csc340.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ReviewId implements Serializable {

    private User user;

    private Provider provider;
}

