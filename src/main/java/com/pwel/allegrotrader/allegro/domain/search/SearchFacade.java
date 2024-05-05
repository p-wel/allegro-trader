package com.pwel.allegrotrader.allegro.domain.search;

import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import com.pwel.allegrotrader.api.finder.model.OfferDto;

import java.util.List;

public interface SearchFacade {

    List<CategoryDto> getCategories(String categoryId);

    List<OfferDto> getOffers(OfferSearchCriteriaParams searchCriteria);
}
