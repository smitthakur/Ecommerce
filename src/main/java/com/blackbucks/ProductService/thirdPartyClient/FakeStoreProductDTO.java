package com.blackbucks.ProductService.thirdPartyClient;

import com.blackbucks.ProductService.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter

public class FakeStoreProductDTO {

    private long id;
    private String title;
    private String description;
    private String image;
    private String category; //should not be 'Category category' as category is defined as string in fakestore.
    private double price;
}
