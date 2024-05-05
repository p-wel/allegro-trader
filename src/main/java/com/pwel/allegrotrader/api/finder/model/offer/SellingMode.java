package com.pwel.allegrotrader.api.finder.model.offer;

import lombok.Builder;

@Builder
public class SellingMode {
    String format;
    Price price;
    FixedPrice fixedPrice;
}
