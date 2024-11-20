package com.blackbucks.ProductService.thirdPartyClient;

import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient{

    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.url}")
    private String fakeStoreURL;

    @Value("${fakestore.api.path.product}")
    private String fakeStoreProductApiPath;

    private String FakeStoreGetProductByIdURL=fakeStoreURL+fakeStoreProductApiPath+"/{id}";
    private String FakeStoreCreateProductURL=fakeStoreURL+fakeStoreProductApiPath;

    private String FakeStoreGetAllCategoriesURL=fakeStoreURL+fakeStoreProductApiPath+"/categories";

    private String FakeStoreGetProductsByCategoryURL=fakeStoreURL+fakeStoreProductApiPath+"/categories/{category}";


    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }



    @Override
    public List<FakeStoreProductDTO> getALlProducts() {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity=
                restTemplate.getForEntity(FakeStoreCreateProductURL,FakeStoreProductDTO[].class);

        List<FakeStoreProductDTO> productList=new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: responseEntity.getBody()){
            productList.add(fakeStoreProductDTO);
        }
        return productList;
    }

    @Override
    public FakeStoreProductDTO getProductById(long id) throws ProductNotFoundException {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity=
                restTemplate.getForEntity(FakeStoreGetProductByIdURL, FakeStoreProductDTO.class,id);

        FakeStoreProductDTO fakeStoreProductDTO=responseEntity.getBody();

        if(fakeStoreProductDTO==null){
            throw new ProductNotFoundException("Product with id: " + id+ " does not exist");
        }

        return fakeStoreProductDTO;
    }

    @Override
    public FakeStoreProductDTO createNewProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity=
                restTemplate.postForEntity(FakeStoreCreateProductURL,genericProductDTO,FakeStoreProductDTO.class);

        FakeStoreProductDTO fakeStoreProductDTO=responseEntity.getBody();

        return fakeStoreProductDTO;
    }

    @Override
    public FakeStoreProductDTO deleteProductById(long id) {

        RestTemplate restTemplate=restTemplateBuilder.build();
//        restTemplate.delete(FakeStoreGetProductByIdURL,FakeStoreProductDTO.class,id);

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity=
                restTemplate.execute(FakeStoreGetProductByIdURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return responseEntity.getBody();
    }

    @Override
    public FakeStoreProductDTO updateProductById(long id, GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity=
                restTemplate.postForEntity(FakeStoreCreateProductURL,genericProductDTO,FakeStoreProductDTO.class);

        FakeStoreProductDTO fakeStoreProductDTO=responseEntity.getBody();

        return fakeStoreProductDTO;
    }

    @Override
    public List<FakeStoreProductDTO> getProductsByCaterogy(String category) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity=
                restTemplate.getForEntity(FakeStoreGetProductsByCategoryURL,FakeStoreProductDTO[].class);

        List<FakeStoreProductDTO> productList=new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: responseEntity.getBody()){
            productList.add(fakeStoreProductDTO);
        }
        return productList;
    }

    @Override
    public List<String> getAllCategories() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<String[]> responseEntity=
                restTemplate.getForEntity(FakeStoreGetAllCategoriesURL,String[].class);

        List<String> categoryList=new ArrayList<>();
        for(String category: responseEntity.getBody()){
            categoryList.add(category);
        }
        return categoryList;
    }
}
