//package com.pwel.allegrotrader.deprecated;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pwel.allegrotrader.allegro.common.JsonBodyFactoryForAllegroRequest;
//import com.pwel.allegrotrader.deprecated.dto.ProductDto;
//import com.pwel.allegrotrader.deprecated.dto.PublishOfferDto;
//import com.pwel.allegrotrader.deprecated.dto.SearchCriteriaDto;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Base64;
//import java.util.Objects;
//
//@Component
//public class AllegroRestClient {
//
//    private static final String URL_WEBSITE_REDIRECTION = "https://api-trader-bfa28.web.app/authenticate";
//    private final String URL_ALLEGRO;
//    private final String URL_ALLEGRO_API;
//    private final String CLIENT_ID;
//    private final String CLIENT_SECRET;
//    private final String HEADERS_V1;
//    private final String BASE_64_CLIENT_ID_SECRET;
//    private final RestTemplate restTemplate;
//    private final ObjectMapper objectMapper;
//
//    public AllegroRestClient(
//            @Value("${allegro.url}") String URL_ALLEGRO,
//            @Value("${allegro.url.api}") String URL_ALLEGRO_API,
//            @Value("${allegro.client.id:}") String CLIENT_ID,
//            @Value("${allegro.client.secret:}") String CLIENT_SECRET,
//            @Value("${allegro.headers.v1}") String HEADERS_V1) {
//        this.URL_ALLEGRO = URL_ALLEGRO;
//        this.URL_ALLEGRO_API = URL_ALLEGRO_API;
//        this.CLIENT_ID = CLIENT_ID;
//        this.CLIENT_SECRET = CLIENT_SECRET;
//        this.HEADERS_V1 = HEADERS_V1;
//        this.restTemplate = new RestTemplate();
//        this.objectMapper = new ObjectMapper();
//        this.BASE_64_CLIENT_ID_SECRET = encodeClientIdSecretToBase64();
//    }
//
//    public ResponseEntity<String> getMainCategories() {
//        HttpEntity<?> request = createRequestWithAppToken();
//        String url = URL_ALLEGRO_API + "/sale/categories";
//        try {
//            return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Failed to retrieve main categories", e);
//        }
//    }
//
//    public ResponseEntity<String> getMainCategories(String bearerToken) {
//        HttpEntity<?> request = createRequestWithBearerToken(bearerToken);
//        String url = URL_ALLEGRO_API + "/sale/categories";
//        try {
//            return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Failed to retrieve main categories", e);
//        }
//    }
//
//    public ResponseEntity<String> getSubcategories(int parentId) {
//        HttpEntity<?> request = createRequestWithAppToken();
//        String url = URL_ALLEGRO_API + "/sale/categories?parent.id=" + parentId;
//        try {
//            return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Failed to retrieve subcategories of '" + parentId + "'", e);
//        }
//    }
//
//    public ResponseEntity<String> getMatchingCategories(ProductDto productDto) {
//        HttpEntity<?> request = createRequestWithAppToken();
//        String url = URL_ALLEGRO_API + "/sale/matching-categories"
//                + "?name=" + productDto.getName();
//        try {
//            return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Failed to search matching categories", e);
//        }
//    }
//
//    public ResponseEntity<String> searchByRequest(SearchCriteriaDto searchCriteriaDto) {
//        HttpEntity<?> request = createRequestWithAppToken();
//        String url = URL_ALLEGRO_API + "/offers/listing"
//                + "?phrase=" + searchCriteriaDto.getPhrase()
//                + "&category.id=" + searchCriteriaDto.getCategoryId()
//                + "&price.from=" + searchCriteriaDto.getPriceFrom()
//                + "&price.to=" + searchCriteriaDto.getPriceTo()
//                + "&limit=" + searchCriteriaDto.getOffersLimit();
//        try {
//            return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Failed to search by request", e);
//        }
//    }
//
//    public ResponseEntity<JSONObject> publishOffer(PublishOfferDto publishOfferDto, String bearerUserToken) {
//        HttpEntity<?> request = createRequestWithBearerTokenAndBody(
//                JsonBodyFactoryForAllegroRequest.createPublishOfferBodyWithGTIN(publishOfferDto).toString(),
//                bearerUserToken);
//        String url = URL_ALLEGRO_API + "/sale/product-offers";
//
//        try {
//            return restTemplate.postForEntity(url, request, JSONObject.class);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Failed to publish an offer. Bearer: " + bearerUserToken + "",
//                    e);
//        }
//    }
//
//    private HttpEntity<?> createRequestWithAppToken() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(getAppToken());
//        headers.set("Accept", HEADERS_V1);
//        headers.set("Content-Type", HEADERS_V1);
//        return new HttpEntity<>(headers);
//    }
//
//    private HttpEntity<?> createRequestWithAppToken(HttpHeaders headers) {
//        headers.setBearerAuth(getAppToken());
//        headers.set("Accept", HEADERS_V1);
//        headers.set("Content-Type", HEADERS_V1);
//        return new HttpEntity<>(headers);
//    }
//
//    private HttpEntity<?> createRequestWithAppTokenAndBody(JSONObject body) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(getAppToken());
//        headers.set("Accept", HEADERS_V1);
//        headers.set("Content-Type", HEADERS_V1);
//        return new HttpEntity<>(body.toString(), headers);
//    }
//
//    public HttpEntity<?> createRequestWithBearerTokenAndBody(Object body, String bearerToken) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(bearerToken);
//        headers.set("Accept", HEADERS_V1);
//        headers.set("Content-Type", HEADERS_V1);
//        return new HttpEntity<>(body, headers);
//    }
//
//    public HttpEntity<?> createRequestWithBearerToken(String bearerToken) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(bearerToken);
//        headers.set("Accept", HEADERS_V1);
//        headers.set("Content-Type", HEADERS_V1);
//        return new HttpEntity<>(headers);
//    }
//
//    private HttpEntity<?> createRequestWithAppToken(Object body, HttpHeaders headers) {
//        headers.setBearerAuth(getAppToken());
//        headers.set("Accept", HEADERS_V1);
//        headers.set("Content-Type", HEADERS_V1);
//        return new HttpEntity<>(body, headers);
//    }
//
//    private String encodeClientIdSecretToBase64() {
//        String stringToEncode = CLIENT_ID + ":" + CLIENT_SECRET;
//        return Base64.getEncoder().encodeToString(stringToEncode.getBytes());
//    }
//
//    public String generateBearerToken(String tenSecondsCode) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "basic " + BASE_64_CLIENT_ID_SECRET);
//        HttpEntity<?> request = new HttpEntity<>(headers);
//        String url = "https://allegro.pl.allegrosandbox.pl/auth/oauth/token"
//                + "?grant_type=authorization_code"
//                + "&code=" + tenSecondsCode
//                + "&redirect_uri=" + URL_WEBSITE_REDIRECTION;
//
//        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.POST, request, JsonNode.class);
//        String bearerToken = Objects.requireNonNull(response.getBody()).get("access_token").asText();
//
//        return bearerToken;
//    }
//
//    @Deprecated(since = "moved to AllegroOAuth2Client")
//    public String getAppToken() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
//
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("grant_type", "client_credentials");
//
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(
//                URL_ALLEGRO + "/auth/oauth/token",
//                requestEntity,
//                String.class);
//        try {
//            JsonNode responseBody = objectMapper.readTree(response.getBody());
//            return responseBody.get("access_token").asText();
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to parse access token response", e);
//        }
//    }
//}
