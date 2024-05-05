package com.pwel.allegrotrader.api.finder.model;

import lombok.Builder;

@Builder
public record OfferDto(
        String id,
        String name
) {}
