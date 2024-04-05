package com.pwel.allegrotrader.allegro.authorization;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwel.allegrotrader.allegro.AllegroClient;
import com.pwel.allegrotrader.allegro.AllegroClientAdapter;
import com.pwel.allegrotrader.allegro.AllegroProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllegroOAuth2ClientConfig {

    @Bean
    AllegroOAuth2Client allegroOAuth2Client(RestTemplateBuilder restTemplateBuilder,
                                ObjectMapper objectMapper,
                                AllegroProperties allegroProperties) {
        return new AllegroOAuth2ClientAdapter(restTemplateBuilder.build(),
                objectMapper,
                allegroProperties
        );
    }
}
