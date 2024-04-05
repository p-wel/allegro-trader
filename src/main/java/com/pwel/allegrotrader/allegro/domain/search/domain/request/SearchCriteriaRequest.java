package com.pwel.allegrotrader.allegro.domain.search.domain.request;

import lombok.Value;

@Value
public class SearchCriteriaRequest {

    String phrase;
    int categoryId;
    double priceFrom;
    double priceTo;
    int offersLimit;

}
