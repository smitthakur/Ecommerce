package com.blackbucks.ProductService.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Price price; // should not be double as double has precision issues. using now because fakeStore uses double


}
