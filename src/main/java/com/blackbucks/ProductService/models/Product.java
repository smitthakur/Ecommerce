package com.blackbucks.ProductService.models;

public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price; // should not be double as double has precision issues. using now because fakeStore uses double


}
