package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.allegro.domain.search.domain.response.AllegroMainCategoriesResponse;

import java.util.List;

public interface AllegroClient {

    List<AllegroMainCategoriesResponse> getMainCategories();
}
