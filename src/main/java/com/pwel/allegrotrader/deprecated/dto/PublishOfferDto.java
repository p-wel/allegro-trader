package com.pwel.allegrotrader.deprecated.dto;

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
