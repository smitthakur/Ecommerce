package com.blackbucks.ProductService.services;

import com.blackbucks.ProductService.Repositories.CategoryRepository;
import com.blackbucks.ProductService.Repositories.ProductRepository;
import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.exceptions.ProductNotFoundException;
import com.blackbucks.ProductService.models.Category;
import com.blackbucks.ProductService.models.Product;
import com.blackbucks.ProductService.thirdPartyClient.FakeStoreProductDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class OgStoreProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public OgStoreProductService(ProductRepository productRepository,
                                 CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
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
    public ResponseEntity<String> createNewProduct(GenericProductDTO genericProductDTO) {
        Product product=new Product();
        product.setTitle(genericProductDTO.getTitle());
        product.setDescription(genericProductDTO.getDescription());
        product.setImage(genericProductDTO.getImage());
        product.setPrice(genericProductDTO.getPrice());

//        Category category = this.categoryRepository.findById(
//                UUID.fromString(genericProductDTO.getCategory())).orElse(null);
//        if(category==null){
        Category category=new Category();
        category.setName(genericProductDTO.getCategory());
            this.categoryRepository.save(category);
//        }
        product.setCategory(category);

        this.productRepository.save(product);

        return new ResponseEntity(HttpStatus.CREATED);
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
