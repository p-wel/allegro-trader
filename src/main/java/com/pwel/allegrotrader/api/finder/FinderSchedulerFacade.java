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

    private final FinderScheduler finderScheduler;
    private String mailText;

    public FinderSchedulerFacade(FinderScheduler finderScheduler) {
        this.finderScheduler = finderScheduler;
        this.mailText = null;
    }

    @Scheduled(fixedRate = SCHEDULER_RATE)
    public void invokeScheduler() {
        if (ACTIVE) {
            finderScheduler.sendMail(mailText);
        }
    }

    public void configureMailMessage(OfferSearchCriteriaParams searchCriteria) {
        mailText = this.finderScheduler.createMailMessage(searchCriteria);
    }
}
