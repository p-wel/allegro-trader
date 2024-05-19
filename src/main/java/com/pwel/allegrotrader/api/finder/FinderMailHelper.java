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
public class FinderMailHelper {

    private final SearchFacadeAdapter searchFacade;
    private final FinderMaiServiceImpl finderMailingService;

    public String createMailMessage(OfferSearchCriteriaParams searchCriteria) {
        var results = searchFacade.getDistinctOffers(searchCriteria);
        return toMailMessage(createMailTemplate(), results);
    }

    public void sendMail(String mailMessage) {
        var to = "pawelx777x@gmail.com";
        var subject = "Finder results";
        finderMailingService.sendSimpleMessage(to, subject, mailMessage);
        log.info("Finder: Mail sent to: %s.".formatted(to));
    }


    private static String createMailTemplate() {
        return "Result %s:\n" +
                "Id: %s\n" +
                "Name: %s\n" +
                "Format: %s\n" +
                "Price: %s %s\n" +
                "FixedPrice: %s %s\n" +
                "VendorLink: %s\n";

    }

    private static String toMailMessage(String resultTemplate, List<ItemDto> results) {
        if (results.isEmpty()) {
            return null;
        }
        var formattedLines = new ArrayList<String>();
        for (int i = 0; i < results.size(); i++) {
            formattedLines.add(formatLine(resultTemplate, results.get(i), i+1));
        }
        return String.join("\n", formattedLines);
    }

    private static String formatLine(String resultTemplate, ItemDto item, int index) {
        return resultTemplate.formatted(index,
                item.getId(),
                item.getName(),
                item.getSellingMode().getFormat(),
                item.getSellingMode().getPrice().getAmount(),
                item.getSellingMode().getPrice().getCurrency(),
                item.getSellingMode().getFixedPrice().getAmount(),
                item.getSellingMode().getFixedPrice().getCurrency(),
                item.getVendorUrl()
        );
    }
}
