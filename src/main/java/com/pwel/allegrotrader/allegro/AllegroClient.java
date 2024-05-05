package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.allegro.domain.search.domain.response.CategoriesResponse;

public interface AllegroClient {

    CategoriesResponse getCategories(String categoryId);
}
