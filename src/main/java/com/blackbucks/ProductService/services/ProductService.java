package com.blackbucks.ProductService.services;

import com.blackbucks.ProductService.thirdPartyClient.FakeStoreProductDTO;
import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public List<GenericProductDTO> getALlProducts();

    public GenericProductDTO getProductById(long id) throws ProductNotFoundException;

    public ResponseEntity createNewProduct(GenericProductDTO genericProductDTO);

    public FakeStoreProductDTO deleteProductById(long id);

    public GenericProductDTO updateProductById(long id, GenericProductDTO genericProductDTO);
}
