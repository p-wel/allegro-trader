package com.pwel.allegrotrader.allegro.authorization;

public interface AllegroOAuth2Client {

    /**
     * bearer-token-for-application
     */
    String getClientCredentials();

    String generateBearerToken(String tenSecondsCode);
}
