package com.blackbucks.ProductService.services;

import com.blackbucks.ProductService.thirdPartyClient.FakeStoreProductServiceClient;
import com.blackbucks.ProductService.thirdPartyClient.FakeStoreProductDTO;
import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.exceptions.ProductNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Primary
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public GenericProductDTO convertFakeStoreProductIntoGenericProduct(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO=new GenericProductDTO();
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice((int)(fakeStoreProductDTO.getPrice()));
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        return genericProductDTO;
    }

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient=fakeStoreProductServiceClient;
    }



    @Override
    public List<GenericProductDTO> getALlProducts() {

        List<FakeStoreProductDTO> fakeStoreProductList=fakeStoreProductServiceClient.getALlProducts();
        List<GenericProductDTO> productList=new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductList){
            productList.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO));
        }
        return productList;
    }

    @Override
    public GenericProductDTO getProductById(long id) throws ProductNotFoundException{
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public ResponseEntity createNewProduct(GenericProductDTO genericProductDTO) {
        ResponseEntity responseEntity=new ResponseEntity(HttpStatus.CREATED);
        return responseEntity;
    }

    @Override
    public FakeStoreProductDTO deleteProductById(long id) {
        return fakeStoreProductServiceClient.deleteProductById(id);
    }

    @Override
    public GenericProductDTO updateProductById(long id, GenericProductDTO genericProductDTO) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.updateProductById(id, genericProductDTO));
    }
}
