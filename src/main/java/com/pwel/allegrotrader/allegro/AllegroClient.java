package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.allegro.domain.search.response.category.CategoriesResponse;
import com.pwel.allegrotrader.allegro.domain.search.response.offer.OfferSearchResponse;

public interface AllegroClient {

    CategoriesResponse getCategories(String categoryId);

    OfferSearchResponse getOffers(OfferSearchCriteriaParams searchCriteria);
}
