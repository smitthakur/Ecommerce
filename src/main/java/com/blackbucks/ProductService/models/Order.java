package com.blackbucks.ProductService.models;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

//@Getter
//@Setter
////@Entity(name = "Order")
//@NoArgsConstructor
//@AllArgsConstructor
public class Order extends BaseModel{
//    @ManyToMany()
//    @JoinTable(
//            name = "Product_Orders",
//            joinColumns = @JoinColumn(name = "Order_id"),
//            inverseJoinColumns = @JoinColumn(name = "Product_id")
//    )
//    private List<Product> productList;
}
