package com.pwel.allegrotrader.api.finder.domain;

import com.pwel.allegrotrader.allegro.domain.search.domain.response.CategoriesResponse;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SearchMapper {

    public List<CategoryDto> toCategoryList(CategoriesResponse response) {
        var list = new ArrayList<CategoryDto>();
        response.categories().forEach(category ->
                list.add(CategoryDto.builder()
                        .id(category.id())
                        .name(category.name())
                        .build())
        );
        return list;
    }
}
