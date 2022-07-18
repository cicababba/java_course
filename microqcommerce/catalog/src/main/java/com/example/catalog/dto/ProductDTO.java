package com.example.catalog.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private long id;

    private String name;

    private String description;

    private float price;
}
