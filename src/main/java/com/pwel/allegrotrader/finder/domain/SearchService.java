package com.pwel.allegrotrader.finder.domain;

import com.pwel.allegrotrader.allegro.AllegroInterface;
import com.pwel.allegrotrader.finder.infrastructure.SearchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final AllegroInterface allegroInterface;

    public List<SearchCriteria> getSearches() {
        return searchRepository.findAll();
    }

    //    public List<AllegroSearchResultDto> searchInAllegro(AllegroSearchRequest allegroSearchRequest) {
//    public ResponseEntity<JsonNode> searchInAllegro(AllegroSearchRequest allegroSearchRequest) {
//        return allegroInterface.searchByParams(allegroSearchRequest);
//    }

    public void setRefreshInterval(SearchCriteria searchCriteria, int days) {
        searchCriteria.setRefreshInterval(days);
    }

    public void setMailing(SearchCriteria searchCriteria) {
        searchCriteria.setMailingEnabled(true);
        log.info("Setting mailing for search criteria");
    }

    public void disableMailing(SearchCriteria searchCriteria) {
        searchCriteria.setMailingEnabled(false);
        log.info("Disabling mailing for search criteria");
    }

    public void addToFavorite(SearchCriteria searchCriteria) {
        searchCriteria.setFavorite(true);
        log.info("Adding search criteria to favorite");
    }

    public void removeFromFavorite(SearchCriteria searchCriteria) {
        searchCriteria.setFavorite(false);
        log.info("Removing search criteria from favorite");
    }

}
