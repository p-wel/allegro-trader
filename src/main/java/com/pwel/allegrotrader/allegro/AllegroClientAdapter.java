package com.pwel.allegrotrader.allegro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwel.allegrotrader.allegro.authorization.AllegroOAuth2Client;
import com.pwel.allegrotrader.allegro.exception.AllegroClientException;
import com.pwel.allegrotrader.allegro.exception.AllegroMappingException;
import com.pwel.allegrotrader.allegro.domain.search.domain.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class AllegroClientAdapter implements AllegroClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AllegroProperties allegroProperties;
    private final AllegroOAuth2Client allegroOAuth2Client;

    @Override
    public List<CategoryResponse> getMainCategories() {
        var bearerAuth = allegroOAuth2Client.getClientCredentials();
        var uri = buildCategoriesUri();
        return executeGet(uri, Map.of(), new TypeReference<>() {}, bearerAuth);
    }

    private String buildCategoriesUri() {
        return UriComponentsBuilder.fromHttpUrl(allegroProperties.urlApi())
                .path(allegroProperties.mainCategoriesPath())
                .build()
                .toUriString();
    }

    private <T> T executeGet(String uri, Map<String, String> uriVariables, TypeReference<T> returnedType, String bearerAuth) {
        var entityWithHeaders = setHeaders(bearerAuth);
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
