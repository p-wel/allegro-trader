package com.pwel.allegrotrader.allegro.authorization

import com.pwel.allegrotrader.allegro.AllegroProperties
import spock.lang.Specification

class AllegroOAuth2ClientAdapterTest extends Specification {

    AllegroProperties allegroProperties

    void setup() {
    }

    def "GetClientCredentials"() {
    }

    def "GenerateBearerToken"() {
//        "pass 'tenSecondsCode' code and run"
//        @Test
//        void testGenerateBearerToken() {
//            var tenSecondsCode = "";
//            String bearerToken = allegroRestClient.generateBearerToken(tenSecondsCode);
//            log.info(bearerToken);
//            assert !bearerToken.isEmpty();
//        }
    }

    def "tenSecCode"() {
        // works in browser, when Allegro User is logged in to Allegro
//        HttpEntity<?> request = new HttpEntity<>(new HttpHeaders());
//        String redirectUrl = allegroProperties.altUrlAuthRedirection();
//        String url = "https://allegro.pl.allegrosandbox.pl/auth/oauth/authorize?response_type=code"
//                + "&client_id=" + allegroProperties.clientId()
//                + "&redirect_uri=" + redirectUrl;
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        assert responseEntity.getBody() != null;
    }
}
