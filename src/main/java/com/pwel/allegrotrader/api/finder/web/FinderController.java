package com.pwel.allegrotrader.api.finder.web;


import com.pwel.allegrotrader.api.finder.domain.SearchCriteria;
import com.pwel.allegrotrader.api.finder.domain.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/v1/finder")
public class FinderController {

    private final SearchService searchService;

    @GetMapping("/searches")
    public List<SearchCriteria> getSearches() {
        return searchService.getSearches();
    }
}
