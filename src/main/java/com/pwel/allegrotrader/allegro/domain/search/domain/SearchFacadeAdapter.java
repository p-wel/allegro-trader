package com.pwel.allegrotrader.allegro.domain.search.domain;

import com.pwel.allegrotrader.allegro.AllegroClient;
import com.pwel.allegrotrader.api.finder.domain.SearchMapper;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SearchFacadeAdapter implements SearchFacade {

    private final AllegroClient allegroClient;

    @Override
    public List<CategoryDto> getCategories() {
        var allegroCategoryList = allegroClient.getMainCategories();
        return SearchMapper.toCategoryList(allegroCategoryList);
    }
}
