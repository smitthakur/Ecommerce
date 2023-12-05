package com.blackbucks.ProductService.controllers;

import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@WebMvcTest  // complete spring boot context will not be created. only context that is needed will be created
@WebMvcTest(ProductController.class)
public class ProductControllerWebMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    public MockHttpServletRequestBuilder mockHttpServletRequestBuilder;

    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception{

        when(productService.getALlProducts())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]"));

    }

    @Test
    void  returnsListOfProductsWhenProductsExist() throws Exception{
        ArrayList<GenericProductDTO> products = new ArrayList<>();
        products.add(new GenericProductDTO());
        products.add(new GenericProductDTO());
        products.add(new GenericProductDTO());

        when(
                productService.getALlProducts()
        ).thenReturn(
                products
        );

        mockMvc.perform(
                get("/products")
        ).andExpect(
                status().is(200)
        ).andExpect(
                content().string(objectMapper.writeValueAsString(products))
        );
    }

    @Test
    void createProductShouldCreateANewProduct() throws Exception {
        GenericProductDTO productToCreate = new GenericProductDTO();
        productToCreate.setTitle("iPhone 15 Pro Max");
        productToCreate.setImage("some image");
        productToCreate.setCategory("mobile phones");
        productToCreate.setDescription("Best iPhone Ever");

        when(
                productService.createNewProduct(any())
        ).thenReturn(
                new ResponseEntity(HttpStatus.CREATED)
        );

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productToCreate))
                ).andExpect(status().is(201));

    }

}
