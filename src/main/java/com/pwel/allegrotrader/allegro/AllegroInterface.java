package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.allegro.dto.CategoryDto;
import com.pwel.allegrotrader.allegro.dto.ProductDto;
import com.pwel.allegrotrader.allegro.dto.PublishOfferDto;
import com.pwel.allegrotrader.allegro.dto.SearchCriteriaDto;
import com.pwel.allegrotrader.commons.SearchResultsDto;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AllegroInterface {

    List<CategoryDto> getMainCategories();

    List<CategoryDto> getSubCategories(int parentId);

    List<CategoryDto> getMatchingCategories(ProductDto productDto);

    List<SearchResultsDto> searchByParams(SearchCriteriaDto searchCriteriaDto);

    ResponseEntity<JSONObject> publishOffer(PublishOfferDto publishOfferDto, String bearerUserToken);

}
