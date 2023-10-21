package com.blackbucks.ProductService.controllers;

import com.blackbucks.ProductService.thirdPartyClient.FakeStoreProductDTO;
import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.exceptions.ProductNotFoundException;
import com.blackbucks.ProductService.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")//This will add /products prefix to all methods mapping
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts(){

        return productService.getALlProducts();
    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") long id) throws ProductNotFoundException { //id is in the url path, to put it inside method use pathvariable
        GenericProductDTO genericProductDTO=productService.getProductById(id);

        return genericProductDTO;
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ExceptionDTO> handleException(){
//        return new ResponseEntity(
//                new ExceptionDTO(HttpStatus.NOT_FOUND,"Product not found"),
//                HttpStatus.NOT_FOUND
//        );
//    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<String> handleException(ProductNotFoundException productNotFoundException){
//
//        return new ResponseEntity(
//                productNotFoundException.getMessage(),
//                HttpStatus.NOT_FOUND
//        );
//    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity handleException(){
//        return new ResponseEntity(
//                HttpStatus.NOT_FOUND
//        );
//    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
        return productService.createNewProduct(genericProductDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FakeStoreProductDTO> deleteProduct(@PathVariable("id") long id){
        return new ResponseEntity<FakeStoreProductDTO>(
                productService.deleteProductById(id),
                HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("{id}")
    public GenericProductDTO updateProduct(@PathVariable("id") long id, @RequestBody GenericProductDTO genericProductDTO){
        return productService.updateProductById(id,genericProductDTO);
    }
}
