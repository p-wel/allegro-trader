package com.pwel.allegrotrader.allegro.domain.search.response.offer;

import lombok.Getter;

@Getter
public class SellingMode {
    String format;
    Price price;
    FixedPrice fixedPrice;
    int popularity;
    String popularityRange;
    int bidCount;
}
