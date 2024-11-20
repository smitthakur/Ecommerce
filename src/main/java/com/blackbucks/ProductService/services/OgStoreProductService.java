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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public GenericProductDTO convertToGenericProductDTO(Product product){
        GenericProductDTO genericProductDTO
                =new GenericProductDTO();
        genericProductDTO.setCategory(product.getCategory().toString());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setPrice(product.getPrice());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setDescription(product.getDescription());

        return genericProductDTO;
    }

    @Override
    public List<GenericProductDTO> getALlProducts() {
        List<Product> products=productRepository.findAll();
        List<GenericProductDTO> genericProductDTOList= new ArrayList<>();
        for(Product product: products){
            genericProductDTOList.add(convertToGenericProductDTO(product));
        }
        return genericProductDTOList;
    }

    @Override
    public GenericProductDTO getProductById(long id) throws ProductNotFoundException {

        Optional<Product> product= productRepository.findById(new UUID(id, id));
        return convertToGenericProductDTO(product.get());

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
        Optional<Product> product= productRepository.findById(new UUID(id,id));
        FakeStoreProductDTO dto=new FakeStoreProductDTO();
        productRepository.deleteById(new UUID(id,id));
        return dto;
    }

    @Override
    public GenericProductDTO updateProductById(long id, GenericProductDTO genericProductDTO) {
        Product product= productRepository.findById(new UUID(id, id)).get();
        Category category= product.getCategory();
        category.setName(genericProductDTO.getCategory());
        product.setCategory(category);
        product.setTitle(product.getTitle());
        product.setPrice(genericProductDTO.getPrice());
        product.setImage(product.getImage());
        productRepository.save(product);
        return convertToGenericProductDTO(product);
    }

    @Override
    public List<GenericProductDTO> getProductsByCategory(String category) {
        Category cat= categoryRepository.findByName(category);
        List<Product> products= productRepository.findAllByCategory(cat);
        List<GenericProductDTO> genericProductDTOList= new ArrayList<>();
        for(Product product: products){
            genericProductDTOList.add(convertToGenericProductDTO(product));
        }
        return genericProductDTOList;
    }

    @Override
    public List<String> getAllProductCategories() {
        List<Category> categories= categoryRepository.findAll();
        List<String> catStringList= new ArrayList<>();
        for(Category category: categories){
            catStringList.add(category.getName());
        }
        return catStringList;
    }
}
