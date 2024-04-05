package com.pwel.allegrotrader.deprecated.dto;

import lombok.Value;

@Value
public class SearchCriteriaDto {

    String phrase;
    int categoryId;
    double priceFrom;
    double priceTo;
    int offersLimit;

}
