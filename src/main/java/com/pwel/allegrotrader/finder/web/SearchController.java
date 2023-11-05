package com.pwel.allegrotrader.finder.web;


import com.pwel.allegrotrader.finder.domain.SearchCriteria;
import com.pwel.allegrotrader.finder.domain.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/v1/searches")
public class SearchController {

    private final SearchService searchService;

    @GetMapping()
    public List<SearchCriteria> getSearches() {
        return searchService.getSearches();
    }

}
