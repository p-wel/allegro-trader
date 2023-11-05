package com.pwel.allegrotrader.commons;

import lombok.Value;

@Value
public class SearchResultsDto {

    int allegroOfferId;
    String allegroOfferName;
    boolean promoted;

}
