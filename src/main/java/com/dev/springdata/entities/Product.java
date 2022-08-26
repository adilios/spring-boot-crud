package com.dev.springdata.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Product {
    @Id
    private int id;

    private String name;

    @Column(name = "description")
    private String desc;

    private Double price;
}
