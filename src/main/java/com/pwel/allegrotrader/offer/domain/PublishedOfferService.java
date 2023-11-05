package com.pwel.allegrotrader.offer.domain;

import com.pwel.allegrotrader.offer.exception.OfferDraftNotFoundException;
import com.pwel.allegrotrader.offer.infrastructure.PublishedOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublishedOfferService {

    private final PublishedOfferRepository publishedOfferRepository;

    public List<PublishedOffer> getPublishedOffers(String title) {
        if (title == null) {
            return publishedOfferRepository.findAll();
        }
        return publishedOfferRepository.findPublishedOffersByTitle(title);
    }

    public PublishedOffer getPublishedOfferById(Long publishedOfferId) {
        return publishedOfferRepository.findById(publishedOfferId)
                .orElseThrow(() -> new OfferDraftNotFoundException(
                        "PublishedOffer with id: " + publishedOfferId + " does not exist."));
    }

}
