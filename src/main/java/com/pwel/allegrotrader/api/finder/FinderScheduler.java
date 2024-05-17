package com.pwel.allegrotrader.api.finder;

import com.pwel.allegrotrader.allegro.domain.search.SearchFacadeAdapter;
import com.pwel.allegrotrader.allegro.domain.search.request.offer.OfferSearchCriteriaParams;
import com.pwel.allegrotrader.api.finder.model.offer.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FinderScheduler {
    // TODO consider moving to 'allegro' package, as no database or api endpoint are used here.
    //  In API, the FinderScheduler will only be used, not implemented like here!
    //  In API, FinderScheduler's results will save last found records to database.
    //  The API will read last found records and compare it with new records found by (used there) FinderScheduler

    private final SearchFacadeAdapter searchFacade;
    private final FinderMailingServiceImpl finderMailingService;

    public String createMailMessage(OfferSearchCriteriaParams searchCriteria) {
        var results = searchFacade.getOffers(searchCriteria);
        return toMailMessage(createMailTemplate(), results);
    }

    public void sendMail(String mailMessage) {
        var to = "pawelx777x@gmail.com";
        var subject = "Finder results";
        finderMailingService.sendSimpleMessage(to, subject, mailMessage);
        log.info("Mailing sent to: %s.".formatted(to));
    }


    private static String createMailTemplate() {
        return "Item %s:\n" +
                "Id: %s\n" +
                "Name: %s\n" +
                "Format: %s\n" +
                "Price: %s %s\n" +
                "FixedPrice: %s %s\n";
    }

    private static String toMailMessage(String resultTemplate, List<ItemDto> results) {
        var line0 = resultTemplate.formatted(0,
                results.get(0).getId(),
                results.get(0).getName(),
                results.get(0).getSellingMode().getFormat(),
                results.get(0).getSellingMode().getPrice().getAmount(),
                results.get(0).getSellingMode().getPrice().getCurrency(),
                results.get(0).getSellingMode().getFixedPrice().getAmount(),
                results.get(0).getSellingMode().getFixedPrice().getCurrency()
        );

        var line1 = resultTemplate.formatted(1,
                results.get(1).getId(),
                results.get(1).getName(),
                results.get(1).getSellingMode().getFormat(),
                results.get(1).getSellingMode().getPrice().getAmount(),
                results.get(1).getSellingMode().getPrice().getCurrency(),
                results.get(1).getSellingMode().getFixedPrice().getAmount(),
                results.get(1).getSellingMode().getFixedPrice().getCurrency()
        );

        var line2 = resultTemplate.formatted(2,
                results.get(2).getId(),
                results.get(2).getName(),
                results.get(2).getSellingMode().getFormat(),
                results.get(2).getSellingMode().getPrice().getAmount(),
                results.get(2).getSellingMode().getPrice().getCurrency(),
                results.get(2).getSellingMode().getFixedPrice().getAmount(),
                results.get(2).getSellingMode().getFixedPrice().getCurrency()
        );
        var list = new ArrayList<String>();
        list.add(line0);
        list.add(line1);
        list.add(line2);
        return line0 + "\n" + line1 + "\n" + line2;
    }
}
