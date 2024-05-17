package com.pwel.allegrotrader.allegro.domain.search.response.offer;

import java.util.ArrayList;

public record OfferSearchResponse(Items items,
                                  Categories categories,
                                  ArrayList<Filter> filters,
                                  SearchMeta searchMeta,
                                  ArrayList<Sort> sort) {
}

