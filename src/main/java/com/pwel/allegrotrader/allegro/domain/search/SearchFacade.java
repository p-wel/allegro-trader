package com.pwel.allegrotrader.allegro.domain.search;

import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import com.pwel.allegrotrader.api.finder.model.offer.ItemDto;

import java.util.List;

public interface SearchFacade {

    List<CategoryDto> getCategories(String categoryId);

    List<ItemDto> getOffers(OfferSearchCriteriaParams searchCriteria);
}
