package com.pwel.allegrotrader.allegro.domain.search;

import com.pwel.allegrotrader.allegro.AllegroClient;
import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.api.finder.domain.SearchMapper;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import com.pwel.allegrotrader.api.finder.model.offer.ItemDto;
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
    public List<ItemDto> getOffers(OfferSearchCriteriaParams searchCriteria) {
        var allegroOffersList = allegroClient.getOffers(searchCriteria);
        var offerList = SearchMapper.toOfferList(allegroOffersList);
        return SearchMapper.distinct(offerList);
    }
}
