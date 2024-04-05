package com.pwel.allegrotrader.api.finder.domain;

import com.pwel.allegrotrader.allegro.domain.search.domain.response.CategoryResponse;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SearchMapper {

    public List<CategoryDto> toCategoryList(List<CategoryResponse> response) {
        var list = new ArrayList<CategoryDto>();
        response.forEach(category ->
                list.add(CategoryDto.builder()
                        .id(category.id())
                        .name(category.name())
                        .build()
                ));
        return list;
    }
}
