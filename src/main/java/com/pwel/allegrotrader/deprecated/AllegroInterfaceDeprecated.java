package com.pwel.allegrotrader.deprecated;

import com.pwel.allegrotrader.deprecated.dto.CategoryDto;
import com.pwel.allegrotrader.deprecated.dto.ProductDto;
import com.pwel.allegrotrader.deprecated.dto.PublishOfferDto;
import com.pwel.allegrotrader.deprecated.dto.SearchCriteriaDto;
import com.pwel.allegrotrader.deprecated.commons.SearchResultsDto;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AllegroInterfaceDeprecated {

    List<CategoryDto> getMainCategories();

    List<CategoryDto> getSubCategories(int parentId);

    List<CategoryDto> getMatchingCategories(ProductDto productDto);

    List<SearchResultsDto> searchByParams(SearchCriteriaDto searchCriteriaDto);

    ResponseEntity<JSONObject> publishOffer(PublishOfferDto publishOfferDto, String bearerUserToken);

}
