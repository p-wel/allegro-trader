package com.pwel.allegrotrader.api.product.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    @Nullable private String category;
    @Nullable private String image;
    @Nullable private LocalDate dateOfCreation;

    public Product(String name, String category, String image) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.dateOfCreation = LocalDate.now();
    }
}
