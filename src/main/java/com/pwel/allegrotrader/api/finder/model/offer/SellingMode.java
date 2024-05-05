package com.pwel.allegrotrader.api.finder.model.offer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SellingMode {
    String format;
    Price price;
    FixedPrice fixedPrice;
}
