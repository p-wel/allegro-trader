package com.pwel.allegrotrader.allegro.authorization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwel.allegrotrader.allegro.AllegroProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Objects;

@RequiredArgsConstructor
public class AllegroOAuth2ClientAdapter implements AllegroOAuth2Client {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AllegroProperties allegroProperties;

    /**
     * App Token
     * "bearer-token-for-application"
     */
    @Override
    public String getClientCredentials() {
        var headers = setClientCredentialsHeaders();
        var body = setClientCredentialsBody();
        var requestEntity = new HttpEntity<>(body, headers);
        var url = allegroProperties.urlWebsite() + "/auth/oauth/token";
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        return retrieveAccessToken(response);
    }

    /**
     * User authorization
     * "bearer-token-for-user"
     */
    @Override
    public String generateBearerToken(String tenSecondsCode) {
        // TODO pass proper values
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "basic " + encodeClientIdSecretToBase64());
//        HttpEntity<?> request = new HttpEntity<>(headers);
//        String url = "https://allegro.pl.allegrosandbox.pl/auth/oauth/token"
//                + "?grant_type=authorization_code"
//                + "&code=" + tenSecondsCode
//                + "&redirect_uri=" + allegroProperties.altUrlAuthRedirection();
//        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.POST, request, JsonNode.class);
//        return Objects.requireNonNull(response.getBody()).get("access_token").asText();
        return null;
    }

    private HttpHeaders setClientCredentialsHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(allegroProperties.clientId(), allegroProperties.clientSecret());
        return headers;
    }

    private MultiValueMap<String, String> setClientCredentialsBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        return body;
    }

    private String retrieveAccessToken(ResponseEntity<String> response) {
        try {
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            return responseBody.get("access_token").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse access token response", e);
        }
    }

    private String encodeClientIdSecretToBase64() {
        // TODO pass proper values
        String stringToEncode = "CLIENT_ID" + ":" + "CLIENT_SECRET";
        return Base64.getEncoder().encodeToString(stringToEncode.getBytes());
    }

    JsonNode mapEntityToJsonNode(ResponseEntity<String> responseEntity, String bodyField) {
        try {
            JsonNode bodyRoot = objectMapper.readTree(responseEntity.getBody());
            return bodyRoot.path(bodyField);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map RestResponse into JsonNode" + e);
        }
    }
}
