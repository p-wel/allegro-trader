package com.pwel.allegrotrader.deprecated.commons;

import lombok.Value;

@Value
public class SearchResultsDto {

    int allegroOfferId;
    String allegroOfferName;
    boolean promoted;
}
