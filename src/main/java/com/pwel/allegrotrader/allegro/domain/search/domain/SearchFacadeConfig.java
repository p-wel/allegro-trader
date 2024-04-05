package com.pwel.allegrotrader.allegro.domain.search.domain;

import com.pwel.allegrotrader.allegro.AllegroClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SearchFacadeConfig {

    @Bean
    SearchFacade searchFacade(AllegroClient allegroClient) {
        return new SearchFacade(allegroClient);
    }
}
