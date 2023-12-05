package com.blackbucks.ProductService.dtos;

import com.blackbucks.ProductService.models.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {

    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private int price;
}
