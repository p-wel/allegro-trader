package com.pwel.allegrotrader.allegro.authorization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwel.allegrotrader.allegro.AllegroProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class AllegroOAuth2ClientAdapter implements AllegroOAuth2Client {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AllegroProperties allegroProperties;

    /**
     * bearer-token-for-application
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
}
