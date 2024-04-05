package com.pwel.allegrotrader.api.offer.web;

import com.pwel.allegrotrader.deprecated.AllegroInterface;
import com.pwel.allegrotrader.deprecated.dto.Currency;
import com.pwel.allegrotrader.deprecated.dto.PublishOfferDto;
import com.pwel.allegrotrader.api.offer.domain.OfferDraft;
import com.pwel.allegrotrader.api.offer.domain.OfferDraftService;
import com.pwel.allegrotrader.api.offer.domain.PublishedOffer;
import com.pwel.allegrotrader.api.offer.domain.PublishedOfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/v1/offer")
public class OfferController {

    private final OfferDraftService offerDraftService;
    private final PublishedOfferService publishedOfferService;
    private final AllegroInterface allegroInterface;
    private final String BEARER_TOKEN_HARDCODED = "";

    @GetMapping("/draft")
    public List<OfferDraft> getOfferDraftsByTitle(
            @RequestParam(required = false) String title
    ) {
        return offerDraftService.getOfferDrafts(title);
    }

    @GetMapping("/draft/{offerDraftId}")
    public OfferDraft getOfferDraft(
            @PathVariable("offerDraftId") Long offerDraftId
    ) {
        return offerDraftService.getOfferDraftById(offerDraftId);
    }

    @PostMapping("/draft/{offerDraftId}")
    public void publishOfferUsingDraft(
            @PathVariable("offerDraftId") Long offerDraftId,
            @RequestBody OfferDraft offerDraft
    ) {
        PublishOfferDto publishOfferDto = new PublishOfferDto(
                "5902719471797",
                "moja nazwa oferty",
                "202293",
                "202293_211441",
                new BigDecimal("229.99"),
                Currency.PLN,
                10
        );
        allegroInterface.publishOffer(publishOfferDto, BEARER_TOKEN_HARDCODED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateOfferDraft(
            @PathVariable("productId") Long offerId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description) {
        offerDraftService.updateOfferDraft(offerId, title, description, new BigDecimal("99.99"));
        return ResponseEntity.ok("Offer Draft Id: " + offerId + " updated successfully");
    }

    @GetMapping("/published")
    public List<PublishedOffer> getPublishedOffersByTitle(
            @RequestParam(required = false) String title
    ) {
        return publishedOfferService.getPublishedOffers(title);
    }

    @GetMapping("/published/{publishedOfferId}")
    public PublishedOffer getPublishedOffer(
            @PathVariable("publishedOfferId") Long publishedOfferId
    ) {
        return publishedOfferService.getPublishedOfferById(publishedOfferId);
    }

}
