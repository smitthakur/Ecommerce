package com.blackbucks.ProductService.services;

import com.blackbucks.ProductService.Repositories.ProductRepository;
import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.exceptions.ProductNotFoundException;
import com.blackbucks.ProductService.models.Category;
import com.blackbucks.ProductService.models.Price;
import com.blackbucks.ProductService.models.Product;
import com.blackbucks.ProductService.thirdPartyClient.FakeStoreProductDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class OgStoreProductService implements ProductService {

    private ProductRepository productRepository;

    public OgStoreProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public List<GenericProductDTO> getALlProducts() {
        return null;
    }

    @Override
    public GenericProductDTO getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity createNewProduct(GenericProductDTO genericProductDTO) {
//        Product product=new Product();
//        product.setTitle(genericProductDTO.getTitle());
//        Category category=new Category();
//        category.setName(genericProductDTO.getCategory());
//        product.setCategory(category);
//        product.setDescription(genericProductDTO.getDescription());
//        product.setImage(genericProductDTO.getImage());
//        Price price=new Price();
//        price.setPrice(genericProductDTO.getPrice());
//        product.setPrice(price);
//
//        productRepository.save(product);
//
//        ResponseEntity responseEntity=new ResponseEntity(HttpStatus.CREATED);
//        return responseEntity;
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    public FakeStoreProductDTO deleteProductById(long id) {
        return null;
    }

    @Override
    public GenericProductDTO updateProductById(long id, GenericProductDTO genericProductDTO) {
        return null;
    }
}
