package com.pwel.allegrotrader.user.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class UserControllerTest {

    private RestTemplate restTemplate = new RestTemplate();
    private String tenSecCode = "";
    private @Value("${allegro.client.id}") String clientId;

    @Test
    void getTenSecCode() {
// works in browser, when Allegro User is logged in to Allegro
//        HttpEntity<?> request = new HttpEntity<>(new HttpHeaders());
//        String redirectUrl = "https://api-trader-bfa28.web.app/authenticate";
//        String url = "https://allegro.pl.allegrosandbox.pl/auth/oauth/authorize?response_type=code"
//                + "&client_id=" + clientId
//                + "&redirect_uri=" + redirectUrl;
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        assert responseEntity.getBody() != null;
    }

    @Test
    void testGenerateUserAuthorizationCode() {
        //
    }

}
