package com.pwel.allegrotrader.allegro.domain.search;

import com.pwel.allegrotrader.allegro.AllegroClient;
import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.api.finder.domain.SearchMapper;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import com.pwel.allegrotrader.api.finder.model.OfferDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SearchFacadeAdapter implements SearchFacade {

    private final AllegroClient allegroClient;

    @Override
    public List<CategoryDto> getCategories(String categoryId) {
        var allegroCategoryList = allegroClient.getCategories(categoryId);
        return SearchMapper.toCategoryList(allegroCategoryList);
    }

    @Override
    public List<OfferDto> getOffers(OfferSearchCriteriaParams searchCriteria) {
        var offersList = allegroClient.getOffers(searchCriteria);
        // TODO mapper
        return List.of();
    }
}
