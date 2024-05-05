package com.pwel.allegrotrader.allegro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwel.allegrotrader.allegro.authorization.AllegroOAuth2Client;
import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.allegro.domain.search.response.offer.OfferSearchResponse;
import com.pwel.allegrotrader.allegro.exception.AllegroClientException;
import com.pwel.allegrotrader.allegro.exception.AllegroMappingException;
import com.pwel.allegrotrader.allegro.domain.search.response.category.CategoriesResponse;
import com.pwel.allegrotrader.api.offer.domain.PublishOfferDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public class AllegroClientAdapter implements AllegroClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AllegroProperties allegroProperties;
    private final AllegroOAuth2Client allegroOAuth2Client;

    @Override
    public CategoriesResponse getCategories(String categoryId) {
        var bearerToken = allegroOAuth2Client.getClientCredentials();
        var uri = buildCategoriesUri(categoryId);
        return executeGet(uri, Map.of(), new TypeReference<>() {}, bearerToken);
    }

    // TODO implement
    @Override
    public int getMatchingCategory(String phrase) {
        return 0;
    }

    @Override
    public OfferSearchResponse getOffers(OfferSearchCriteriaParams searchCriteria) {
        var bearerToken = allegroOAuth2Client.getClientCredentials();
        var uri = buildOfferSearchUri(searchCriteria);
        return executeGet(uri, Map.of(), new TypeReference<>() {}, bearerToken);
    }

    // TODO pass proper values
    @Override
    public ResponseEntity<JSONObject> publishOffer(PublishOfferDto publishOfferDto, String bearerUserToken) {
//        HttpEntity<?> request = createRequestWithBearerTokenAndBody(
//                JsonBodyFactoryForAllegroRequest.createPublishOfferBodyWithGTIN(publishOfferDto).toString(),
//                bearerUserToken);
//        String url = URL_ALLEGRO_API + "/sale/product-offers";
//
//        try {
//            return restTemplate.postForEntity(url, request, JSONObject.class);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Failed to publish an offer. Bearer: " + bearerUserToken, e);
//        }
        return null;
    }

    private String buildCategoriesUri(String categoryId) {
        return UriComponentsBuilder.fromHttpUrl(allegroProperties.urlApi())
                .path(allegroProperties.categoriesPath())
                .queryParam("parent.id", categoryId)
                .build()
                .toUriString();
    }

    private String buildOfferSearchUri(OfferSearchCriteriaParams searchCriteria) {
        return UriComponentsBuilder.fromHttpUrl(allegroProperties.urlApi())
                .path(allegroProperties.offerSearchPath())
                .queryParam("category.id", searchCriteria.categoryId())
                .queryParam("phrase", searchCriteria.phrase())
//                .queryParam("seller.id", searchCriteria.sellerId())
//                .queryParam("seller.login", searchCriteria.sellerLogin())
                .queryParam("marketplaceId", searchCriteria.marketplaceId())
                .queryParam("shipping.country", searchCriteria.shippingCountry())
                .queryParam("currency", searchCriteria.currency())
                .queryParam("searchMode", searchCriteria.searchMode())
                .queryParam("offset", searchCriteria.offset())
                .queryParam("limit", searchCriteria.limit())
                .queryParam("sort", searchCriteria.sort())
                .queryParam("include", searchCriteria.include())
                .queryParam("fallback", searchCriteria.fallback())
//                .queryParam("dynamicFilters", searchCriteria.dynamicFilters().id()) //TODO dynamicFilters query param name seems to be invalid
                .build()
                .toUriString();
    }

    private <T> T executeGet(String uri, Map<String, String> uriVariables, TypeReference<T> returnedType, String bearerToken) {
        var entityWithHeaders = setHeaders(bearerToken);
        String response;
        try {
            response = restTemplate.exchange(uri, HttpMethod.GET, entityWithHeaders, String.class, uriVariables).getBody();
        } catch(RestClientException e) {
            throw new AllegroClientException("Allegro client was unable to execute GET response. Reason: %s".formatted(e.getMessage()), e);
        }
        return parseValue(response, returnedType);
    }

    private HttpEntity<HttpHeaders> setHeaders(String bearerAuth) {
        var headers = new HttpHeaders();
        headers.setBearerAuth(bearerAuth);
        headers.setContentType(MediaType.valueOf(allegroProperties.allegroContentType()));
        headers.setAccept(Collections.singletonList(MediaType.valueOf(allegroProperties.allegroContentType())));
        return new HttpEntity<>(headers);
    }

    private <T> T parseValue(String retrievedResponse, TypeReference<T> returnedType) {
        try {
            return objectMapper.readValue(retrievedResponse, returnedType);
        } catch (IOException e) {
            throw new AllegroMappingException("Allegro client was unable to map retrieved message to object. Reason: %s".formatted(
                    e.getMessage()), e);
        } catch (IllegalArgumentException e) {
            throw new AllegroMappingException("Allegro client retrieved message was null", e);
        }
    }
}
