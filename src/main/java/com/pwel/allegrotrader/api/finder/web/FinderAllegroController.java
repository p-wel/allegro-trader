package com.pwel.allegrotrader.api.finder.web;


import com.pwel.allegrotrader.allegro.domain.search.SearchFacade;
import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.api.finder.FinderSchedulerFacade;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import com.pwel.allegrotrader.api.finder.model.offer.ItemDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@Validated
@AllArgsConstructor
@RequestMapping("api/v1/finder/allegro/")
public class FinderAllegroController {

    private final SearchFacade searchFacade;
    private final FinderSchedulerFacade finderSchedulerFacade;

    @PostMapping("/enable")
    public void enableFinder(@RequestBody OfferSearchCriteriaParams searchCriteria) {
        log.info("Controller: enable scheduler Finder with phrase: '{}'", searchCriteria.phrase());
        finderSchedulerFacade.setSearchCriteriaForMailing(searchCriteria);
        FinderSchedulerFacade.ACTIVE = true;
    }

    @GetMapping("/disable")
    public void disableFinder() {
        log.info("Controller: disable scheduler Finder");
        FinderSchedulerFacade.ACTIVE = false;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(
            @Valid @RequestParam(required = false) String categoryId
    ) {
        log.info("Controller: getCategories with categoryId: {}", categoryId);
        return searchFacade.getCategories(categoryId);
    }

    @GetMapping("/offers")
    public List<ItemDto> getOffers(
            @Valid @RequestParam String categoryId,
            @Valid @RequestParam String phrase,
//            @Valid @RequestParam String sellerId,
//            @Valid @RequestParam String sellerLogin,
            @Valid @RequestParam String marketplaceId,
            @Valid @RequestParam String shippingCountry,
            @Valid @RequestParam String currency,
            @Valid @RequestParam String searchMode,
            @Valid @RequestParam Integer offset,
            @Valid @RequestParam Integer limit,
            @Valid @RequestParam String sort,
            @Valid @RequestParam String include,
            @Valid @RequestParam Boolean fallback
//            @Valid @RequestParam String dynamicFilterId
    ) {
        log.info("Controller: getOffers with phrase: {}", phrase);
        var searchCriteria = OfferSearchCriteriaParams.builder()
                .categoryId(categoryId)
                .phrase(phrase)
//                .sellerId(sellerId)
//                .sellerLogin(sellerLogin)
                .marketplaceId(marketplaceId)
                .shippingCountry(shippingCountry)
                .currency(currency)
                .searchMode(searchMode)
                .offset(offset)
                .limit(limit)
                .sort(sort)
                .include(include)
                .fallback(false)
//                .dynamicFilters(new DynamicFilter(dynamicFilterId))
                .build();
        return searchFacade.getOffers(searchCriteria);
    }
}
