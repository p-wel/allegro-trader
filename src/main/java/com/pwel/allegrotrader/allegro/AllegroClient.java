package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.allegro.domain.search.response.category.CategoriesResponse;
import com.pwel.allegrotrader.allegro.domain.search.response.offer.OfferSearchResponse;
import com.pwel.allegrotrader.api.offer.domain.PublishOfferDto;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

public interface AllegroClient {

    CategoriesResponse getCategories(String categoryId);

    int getMatchingCategory(String phrase);

    OfferSearchResponse getOffers(OfferSearchCriteriaParams searchCriteria);

    ResponseEntity<JSONObject> publishOffer(PublishOfferDto publishOfferDto, String bearerUserToken);
}
