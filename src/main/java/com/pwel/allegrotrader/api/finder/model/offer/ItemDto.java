package com.pwel.allegrotrader.api.finder.model.offer;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ItemDto {
    @NonNull String id;
    @NonNull String name;
    SellingMode sellingMode;
}
