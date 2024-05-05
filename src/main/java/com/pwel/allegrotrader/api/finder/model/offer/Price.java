package com.pwel.allegrotrader.api.finder.model.offer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Price {
    String amount;
    String currency;
}
