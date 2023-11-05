package com.pwel.allegrotrader.finder.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class SearchCriteria {

    @Id
    @SequenceGenerator(
            name = "search_criteria_sequence",
            sequenceName = "search_criteria_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_criteria_sequence"
    )
    private Long id;
    private String searchPhrase;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String category;
    private String city;
    private LocalDate publishDateFrom;
    private LocalDate publishDateTo;
    private int refreshInterval;
    private boolean mailingEnabled;
    private boolean favorite;

    public SearchCriteria(String searchPhrase, BigDecimal minPrice, BigDecimal maxPrice, String category, String city, LocalDate publishDateFrom, LocalDate publishDateTo) {
        this.searchPhrase = searchPhrase;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.category = category;
        this.city = city;
        this.publishDateFrom = publishDateFrom;
        this.publishDateTo = publishDateTo;
    }

    public SearchCriteria(String searchPhrase, BigDecimal minPrice, BigDecimal maxPrice, String category, String city) {
        this.searchPhrase = searchPhrase;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.category = category;
        this.city = city;
    }
}
