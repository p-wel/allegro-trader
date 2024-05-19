package com.pwel.allegrotrader.api.finder;

import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FinderSchedulerFacade {

    @Setter
    public static boolean ACTIVE = false;
    private final static long SCHEDULER_RATE = 10_000L; // 10s
    public static OfferSearchCriteriaParams searchCriteria = null;

    private final FinderScheduler finderScheduler;

    public FinderSchedulerFacade(FinderScheduler finderScheduler) {
        this.finderScheduler = finderScheduler;
    }

    @Scheduled(fixedRate = SCHEDULER_RATE)
    public void invokeScheduler() {
        if (ACTIVE) {
            var mailText = finderScheduler.createMailMessage(searchCriteria);
            finderScheduler.sendMail(mailText);
        }
    }

    public void setSearchCriteriaForMailing(OfferSearchCriteriaParams searchCriteriaParams) {
        searchCriteria = searchCriteriaParams;
    }
}
