package com.pwel.allegrotrader.offer;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class OfferControllerTest {

    private String API_URL = "http://127.0.0.1:8080/api/v1/";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void getAll() {
        String url = API_URL + "offer/draft";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<JsonNode> request = new HttpEntity<>(headers);
        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class);
        assert response.getStatusCode() == HttpStatus.OK;
    }

}
