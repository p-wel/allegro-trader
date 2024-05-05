package com.pwel.allegrotrader.api.finder.web;


import com.pwel.allegrotrader.allegro.domain.search.domain.SearchFacade;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@AllArgsConstructor
@RequestMapping("api/v1/finder/allegro/")
public class FinderAllegroController {

    private final SearchFacade searchFacade;

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(
            @Valid @RequestParam(required = false) String categoryId
    ) {
        return searchFacade.getCategories(categoryId);
    }
}
