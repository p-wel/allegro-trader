package com.pwel.allegrotrader.allegro.domain.search.domain.response;

import lombok.Builder;

import java.util.ArrayList;

@Builder
public record CategoriesResponse(ArrayList<Category> categories) {

}
