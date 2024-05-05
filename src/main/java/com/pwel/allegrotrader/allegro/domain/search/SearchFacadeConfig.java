package com.pwel.allegrotrader.allegro.domain.search;

import com.pwel.allegrotrader.allegro.AllegroClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SearchFacadeConfig {

    @Bean
    SearchFacadeAdapter searchFacade(AllegroClient allegroClient) {
        return new SearchFacadeAdapter(allegroClient);
    }
}
