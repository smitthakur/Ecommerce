package com.blackbucks.ProductService.thirdPartyClient;

import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.exceptions.ProductNotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceClient {

    public List<FakeStoreProductDTO> getALlProducts();

    public FakeStoreProductDTO getProductById(long id) throws ProductNotFoundException;

    public FakeStoreProductDTO createNewProduct(GenericProductDTO genericProductDTO);

    public FakeStoreProductDTO deleteProductById(long id);

    public FakeStoreProductDTO updateProductById(long id, GenericProductDTO genericProductDTO);
}
