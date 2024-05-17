package com.pwel.allegrotrader.api.finder.model;

import lombok.Builder;

@Builder
public record CategoryDto(
        String id,
        String name
) {

}
