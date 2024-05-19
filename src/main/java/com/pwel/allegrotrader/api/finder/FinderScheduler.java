package com.pwel.allegrotrader.api.finder;

import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FinderScheduler {

    @Setter
    public static boolean ACTIVE = false;
    private final static long SCHEDULER_RATE = 60_000L; // 60s
    public static OfferSearchCriteriaParams searchCriteria = null;

    private final FinderMailHelper finderMailHelper;

    public FinderScheduler(FinderMailHelper finderMailHelper) {
        this.finderMailHelper = finderMailHelper;
    }

    @Scheduled(fixedRate = SCHEDULER_RATE)
    public void invokeScheduler() {
        if (ACTIVE) {
            var mailText = finderMailHelper.createMailMessage(searchCriteria);
            finderMailHelper.sendMailIfNew(mailText);
        }
    }

    public void setSearchCriteriaForMailing(OfferSearchCriteriaParams searchCriteriaParams) {
        searchCriteria = searchCriteriaParams;
    }
}
