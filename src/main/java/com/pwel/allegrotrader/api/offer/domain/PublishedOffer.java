package com.pwel.allegrotrader.api.offer.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class PublishedOffer {

    @Id
    @SequenceGenerator(
            name = "published_offer_sequence",
            sequenceName = "published_offer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "published_offer_sequence"
    )
    private Long id;
    @Nullable private Long allegroId;
    @Nullable private Long productId;
    @Nullable private String title;
    @Nullable private String description;
    @Nullable private BigDecimal price;
    @Nullable private Status status;

    public PublishedOffer(Long allegroId, Long productId, String title, String description, BigDecimal price, Status status) {
        this.allegroId = allegroId;
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
    }
}
