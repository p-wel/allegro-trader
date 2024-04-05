package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.allegro.domain.search.domain.response.CategoryResponse;

import java.util.List;

public interface AllegroClient {

    List<CategoryResponse> getMainCategories();
}
