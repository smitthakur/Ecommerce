package com.blackbucks.ProductService.controllers;

import com.blackbucks.ProductService.dtos.GenericProductDTO;
import com.blackbucks.ProductService.exceptions.ProductNotFoundException;
import com.blackbucks.ProductService.services.FakeStoreProductService;
import com.blackbucks.ProductService.thirdPartyClient.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;

//import static org.junit.jupiter.api.AssertNull.assertNull;
//import static org.junit.jupiter.api.AssertThrows.assertThrows;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNull;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    @MockBean
    private FakeStoreProductServiceClient fakeStoryProductServiceClient;

    @Autowired
    private ProductController productController;


//    @MockBean
    @MockBean
    private FakeStoreProductService productService;

    @MockBean
    private FakeStoreProductService fakeStoreProductService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Captor
    private ArgumentCaptor<Long> fakeStoreCaptor;

    @Test
    void returnsNullWhenProductDoesntExist() throws ProductNotFoundException {
        when(
                productService.getProductById(any(Long.class))
        ).thenReturn(null);


//        GenericProductDTO genericProductDto = productController.getProductById(121L);
//        given


        assertThrows(ProductNotFoundException.class,()->productController.getProductById(121L));
    }

    @Test
    void throwsExceptionWhenProductDoesntExist() throws ProductNotFoundException {
        when(
                productService.getProductById(any(Long.class))
        )
                .thenReturn(null);

        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(123L));
    }

    @Test
    void returnsSameProductAsServiceWhenProductExists() throws ProductNotFoundException {
        GenericProductDTO genericProductDto = new GenericProductDTO();
        when(
                productService.getProductById(any(Long.class))
        )
                .thenReturn(genericProductDto);

        assertEquals(genericProductDto.getPrice(), productController.getProductById(123L).getPrice());

        assertThrows(ChangeSetPersister.NotFoundException.class, () -> productController.getProductById(123L));
    }

    @Test
    void shouldReturnTitleNamanWithProductID1() throws ProductNotFoundException {
        GenericProductDTO genericProductDto = new GenericProductDTO();
        genericProductDto.setTitle("Naman");
        when(
                productService.getProductById(1L)
        ).thenReturn(
                genericProductDto
        );


        GenericProductDTO genericProductDto1 = productController.getProductById(1L);
        assertEquals("Naman", genericProductDto1.getTitle());
    }

    @Test
    @DisplayName("1 + 1 equals 2")
    void onePlusOneEqualsTrue() throws ProductNotFoundException {
//        System.out.println("It is true");
//        assertEquals(11, 1 + 1, "one plus is not coming to be 11");
//
//        assert
//
//        assertNull(fakeStoryProductServiceClient.getProductById(101L));
//
//        Exception e;
//
//        try {
//            fakeStoryProductServiceClient.getProductById(101L);
//        } catch (Exception ex) {
//            e = ex;
//        }
//
//        assertNotNull(e);
//        assertEquals(ProductNotFoundException.class, e.getClass());
//
//        assertEquals(null, fakeStoryProductServiceClient.getProductById(101L));
//        assertThrows(ProductNotFoundException.class, () -> fakeStoryProductServiceClient.getProductById(101L));
//
//        assertEquals(true, 1 + 1 == 2);
//        assertTrue(returnSomething());
    }

    boolean returnSomething() {
        Random random = new Random();
        return random.nextInt() % 2 == 0;
    }

    @Test
    void additionShouldBeCorrect() {
        assertTrue(-1 + -1 == -2, "adding 2 negatives is not correct");

        assertTrue(-1 + 0 == -1, "adding a negative and a zero is giving wrong answer");

        assertTrue(-1 + 1 == 0);

        assert 1 + 0 == 1;

        assert 1 + 1 == 2;
    }

    @Test
    void productControllerCallsProductServiceWithSameProductId() throws ProductNotFoundException {
        Long id = 1L;

        when(fakeStoryProductServiceClient.getProductById(any()))
                .thenCallRealMethod();
        when(productService.getProductById(any()))
                .thenCallRealMethod();


        when(fakeStoreProductService.getProductById(any()))
                .thenCallRealMethod();

//         check that the product service is being called with the exact same
//         param as controller

        productController.getProductById(id);

        verify(productService).getProductById(idCaptor.capture());
        verify(fakeStoryProductServiceClient).getProductById(fakeStoreCaptor.capture());
        assertEquals(id, idCaptor.getValue());
        assertEquals(id, fakeStoreCaptor.getValue());
    }
}
