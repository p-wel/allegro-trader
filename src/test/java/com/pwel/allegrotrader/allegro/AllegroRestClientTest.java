package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.deprecated.AllegroRestClient;
import com.pwel.allegrotrader.deprecated.dto.Currency;
import com.pwel.allegrotrader.deprecated.dto.ProductDto;
import com.pwel.allegrotrader.deprecated.dto.PublishOfferDto;
import com.pwel.allegrotrader.deprecated.dto.SearchCriteriaDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
class AllegroRestClientTest {

    @Autowired
    private AllegroRestClient allegroRestClient;

    private @Value("${bearer.hardcoded}") String BEARER_TOKEN_HARDCODED;

    @Test
    void testGetMainCategories() {
        var response = allegroRestClient.getMainCategories();
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testUsingBearerTokenOnGetMainCategories() {
        var response = allegroRestClient.getMainCategories(BEARER_TOKEN_HARDCODED);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testGetSubcategories() {
        var response = allegroRestClient.getSubcategories(1);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testGetMatchingCategories() {
        ProductDto productDto = new ProductDto("logitech");
        var response = allegroRestClient.getMatchingCategories(productDto);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testSearchByRequest() {
        SearchCriteriaDto searchCriteriaDto = new SearchCriteriaDto(
                "logitech",
                259434,
                10.25,
                1000.99,
                10
        );
        var response = allegroRestClient.searchByRequest(searchCriteriaDto);
        assert response.getStatusCode() == HttpStatus.OK;
    }

//    "pass 'tenSecondsCode' code and run"
    @Test
    void testGenerateBearerToken() {
        var tenSecondsCode = "";
        String bearerToken = allegroRestClient.generateBearerToken(tenSecondsCode);
        log.info(bearerToken);
        assert !bearerToken.isEmpty();
    }

    @Test
    void testPublishOffer() {
        PublishOfferDto publishOfferDto = new PublishOfferDto(
                "5902719471797",
                "moja nazwa oferty",
                "202293",
                "202293_211441",
                new BigDecimal("227.99"),
                Currency.PLN,
                10
        );
        allegroRestClient.publishOffer(publishOfferDto, BEARER_TOKEN_HARDCODED);
    }

    @Test
    void testGetAppToken() {
        String accessToken = allegroRestClient.getAppToken();
        assert accessToken != null;
    }

}
