package com.pwel.allegrotrader.allegro.domain.search.domain.response;

import lombok.Builder;

@Builder
public record AllegroMainCategoriesResponse(
        int id,
        String name
) {}
