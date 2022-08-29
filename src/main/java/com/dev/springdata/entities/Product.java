package com.dev.springdata.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(name = "description")
    private String desc;

    private Double price;
}
