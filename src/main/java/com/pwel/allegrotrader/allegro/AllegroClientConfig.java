package com.pwel.allegrotrader.allegro;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwel.allegrotrader.allegro.authorization.AllegroOAuth2Client;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllegroClientConfig {

    @Bean
    AllegroClient allegroClient(RestTemplateBuilder restTemplateBuilder,
                                ObjectMapper objectMapper,
                                AllegroProperties allegroProperties,
                                AllegroOAuth2Client allegroOAuth2Client) {
        return new AllegroClientAdapter(restTemplateBuilder.build(),
                objectMapper,
                allegroProperties,
                allegroOAuth2Client
        );
    }
}
