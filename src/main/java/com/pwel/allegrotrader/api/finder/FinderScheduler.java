package com.pwel.allegrotrader.api.finder;

import com.pwel.allegrotrader.allegro.domain.search.SearchFacadeAdapter;
import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FinderScheduler {

    private static final long SCHEDULER_RATE = 5_000L; // 5s

    private final SearchFacadeAdapter searchFacade;

    @Scheduled(fixedRate = SCHEDULER_RATE)
    public void getOffersSchedule() {
        var searchCriteria = OfferSearchCriteriaParams.builder()
                .categoryId("4")
                .phrase("smartphone")
                .marketplaceId("allegro-pl")
                .shippingCountry("PL")
                .currency("PLN")
                .searchMode("REGULAR")
                .offset(0)
                .limit(3)
                .sort("relevance")
                .include("all")
                .fallback(false)
                .build();
        var results = searchFacade.getOffers(searchCriteria);
        var price0 = results.get(0).getSellingMode().getPrice().getAmount();
        var price1 = results.get(0).getSellingMode().getPrice().getAmount();
        var price2 = results.get(0).getSellingMode().getPrice().getAmount();

        log.info("ZXC: " + "[" + price0 + "] " + results.get(0).getName());
        log.info("ZXC: " + "[" + price1 + "] " + results.get(1).getName());
        log.info("ZXC: " + "[" + price2 + "] " + results.get(2).getName());
    }
}
