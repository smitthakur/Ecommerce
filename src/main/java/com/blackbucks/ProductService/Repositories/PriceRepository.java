package com.blackbucks.ProductService.Repositories;

import com.blackbucks.ProductService.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price,Long> {
}
