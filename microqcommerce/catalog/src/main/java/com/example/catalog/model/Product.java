package com.example.catalog.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private long id;

    private String name;

    private String description;

    private float price;
}
