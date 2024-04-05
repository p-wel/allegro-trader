package com.pwel.allegrotrader.allegro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwel.allegrotrader.allegro.exception.AllegroClientException;
import com.pwel.allegrotrader.allegro.exception.AllegroMappingException;
import com.pwel.allegrotrader.allegro.domain.search.domain.response.AllegroMainCategoriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class AllegroClientAdapter implements AllegroClient {

    // TODO: move to AllegroProperties
    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String ALLEGRO_SITE_URL = "";
    private static final String HEADERS_V1 = "";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final AllegroProperties allegroProperties;

    @Override
    public List<AllegroMainCategoriesResponse> getMainCategories() {
        String uri = buildCategoriesUri();
        return executeGet(uri, null, new TypeReference<>() {});
    }

    private String buildCategoriesUri() {
        return UriComponentsBuilder.fromHttpUrl(allegroProperties.hostApi())
                .path(allegroProperties.mainCategoriesPath())
                .build()
                .toUriString();
    }

    private <T> T executeGet(String uri, Map<String, String> uriVariables, TypeReference<T> returnedType) {
        var entityWithHeaders = setHeaders();
        String response;
        try {
            response = restTemplate.exchange(uri, HttpMethod.GET, entityWithHeaders, String.class, uriVariables).getBody();
        } catch(RestClientException e) {
            throw new AllegroClientException("Allegro client was unable to execute GET response. Reasone: %s".formatted(e.getMessage()), e);
        }
        return parseValue(response, returnedType);
    }

    private HttpEntity<HttpHeaders> setHeaders() {
        var headers = new HttpHeaders();
        headers.setBearerAuth(getAppToken());
        headers.set("Accept", allegroProperties.allegroHeadersV1());
        headers.set("Content-Type", HEADERS_V1);
        return new HttpEntity<>(headers);
    }

    private String getAppToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                ALLEGRO_SITE_URL + "/auth/oauth/token",
                requestEntity,
                String.class);
        try {
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            return responseBody.get("access_token").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse access token response", e);
        }
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
