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
public class OfferDraft {

    @Id
    @SequenceGenerator(
            name = "offer_draft_sequence",
            sequenceName = "offer_draft_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "offer_draft_sequence"
    )
    private Long id;
    @Nullable
    private Long productId;
    private String title;
    private String description;
    private BigDecimal price;

    public OfferDraft(Long productId, String title, String description, BigDecimal price) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
