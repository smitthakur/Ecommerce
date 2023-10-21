package com.blackbucks.ProductService.dtos;

import com.blackbucks.ProductService.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {

    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
