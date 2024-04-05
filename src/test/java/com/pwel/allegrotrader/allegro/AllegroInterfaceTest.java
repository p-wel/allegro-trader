package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.deprecated.AllegroInterface;
import com.pwel.allegrotrader.deprecated.dto.Currency;
import com.pwel.allegrotrader.deprecated.dto.ProductDto;
import com.pwel.allegrotrader.deprecated.dto.PublishOfferDto;
import com.pwel.allegrotrader.deprecated.dto.SearchCriteriaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Objects;

@SpringBootTest
class AllegroInterfaceTest {

    @Autowired
    private AllegroInterface allegroInterface;
    private @Value("${bearer.hardcoded}") String BEARER_TOKEN_HARDCODED;

    @Test
    void testGetSubCategories() {
        var categoryList = allegroInterface.getSubCategories(1);
        assert !categoryList.isEmpty();
        assert Objects.equals(categoryList.get(0).getName(), "Płyty kompaktowe");
    }

    @Test
    void testGetMatchingCategories() {
        ProductDto productDto = new ProductDto("logitech");
        var matchingCategoryList = allegroInterface.getMatchingCategories(productDto);
        assert !matchingCategoryList.isEmpty();
        assert matchingCategoryList.get(0).getId() == 314093;
        assert Objects.equals(matchingCategoryList.get(0).getName(), "Etui i pokrowce na słuchawki");
    }

    @Test
    void testSearchByParamsWithLimit() {
        int offersLimit = 10;
        SearchCriteriaDto requestParams = new SearchCriteriaDto(
                "logitech",
                259434,
                10.25,
                1000.99,
                offersLimit
        );
        var searchResults = allegroInterface.searchByParams(requestParams);
        assert !searchResults.isEmpty();
        assert !searchResults.get(0).getAllegroOfferName().isEmpty();
        assert searchResults.size() == offersLimit;
    }

    @Test
    void testMapFromAllegroRestResponse() {
        var categoryList = allegroInterface.getMainCategories();
        assert !categoryList.isEmpty();
        assert Objects.equals(categoryList.get(0).getName(), "Dom i Ogród");
    }

    @Test
    void testPublishOffer() {
        PublishOfferDto publishOfferDto = new PublishOfferDto(
                "5902719471797",
                "moja nazwa oferty",
                "202293",
                "202293_211441",
                new BigDecimal("229.99"),
                Currency.PLN,
                10
        );

        var response = allegroInterface.publishOffer(publishOfferDto, BEARER_TOKEN_HARDCODED);
        assert response.getStatusCode() == HttpStatus.ACCEPTED;
    }
}
