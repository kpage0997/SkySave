package com.csc340.provider;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "providers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Provider {

    @Id
    private String name;

    private String address;

}
