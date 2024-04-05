package com.pwel.allegrotrader.allegro.domain.search.web;


import com.pwel.allegrotrader.allegro.domain.search.domain.SearchFacade;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/v1/allegro")
public class SearchController {

    private final SearchFacade searchFacade;

    @GetMapping("/categories")
    public List<CategoryDto> getCategories() {
        return searchFacade.getCategories();
    }
}