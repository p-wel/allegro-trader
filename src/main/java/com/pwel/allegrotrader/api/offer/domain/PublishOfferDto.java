package com.pwel.allegrotrader.api.offer.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class PublishOfferDto {

    String gtinId;
    String name;
    String parametersId;
    String parametersValuesId;
    BigDecimal price;
    Currency currency;
    int quantity;

}

enum Currency {
    PLN,
    EUR,
    USD,
    CHF,
    GBP,
    NOK
}
