package com.pwel.allegrotrader.api.finder.model;

import lombok.Builder;

@Builder
public record CategoryDto(
        int id,
        String name
) {}
