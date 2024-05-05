package com.pwel.allegrotrader.allegro.domain.search.domain;

import com.pwel.allegrotrader.api.finder.model.CategoryDto;

import java.util.List;

public interface SearchFacade {

    List<CategoryDto> getCategories(String categoryId);
}
