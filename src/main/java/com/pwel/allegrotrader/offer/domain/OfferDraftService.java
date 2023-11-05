package com.pwel.allegrotrader.offer.domain;

import com.pwel.allegrotrader.offer.exception.OfferDraftNotFoundException;
import com.pwel.allegrotrader.offer.infrastructure.OfferDraftRepository;
import com.pwel.allegrotrader.product.domain.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OfferDraftService {

    private final OfferDraftRepository offerDraftRepository;

    public List<OfferDraft> getOfferDrafts(String title) {
        if (title == null) {
            return offerDraftRepository.findAll();
        }
        return offerDraftRepository.findOfferDraftsByTitle(title);
    }

    public OfferDraft getOfferDraftById(Long offerDraftId) {
        return offerDraftRepository.findById(offerDraftId)
                .orElseThrow(() -> new OfferDraftNotFoundException(
                        "OfferDraft with id: " + offerDraftId + " does not exist."));
    }

    @Transactional
    public void updateOfferDraft(Long productId, String title, String description, BigDecimal price) {
        OfferDraft offerDraft = offerDraftRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + productId + " does not exist."));

        if (title != null && title.length() > 0) {
            Optional<OfferDraft> offerDraftOptional = offerDraftRepository
                    .findOfferDraftByTitle(title);
            if (!Objects.equals(offerDraft.getTitle(), title)) {
                if (offerDraftOptional.isPresent()) {
                    throw new IllegalStateException("Product title already taken.");
                }
                offerDraft.setTitle(title);
                log.info("Product: {} has been updated with new title: {}", offerDraft.getTitle(), title);
            }
        }

        if (price != null) {
            if (price.intValue() <= 0) {
                throw new IllegalStateException("Price value must be more than 0.00.");
            }
            offerDraft.setPrice(price);
            log.info("Product: {} has been updated with new price: {}", offerDraft.getTitle(), price);
        }

        if (description != null && description.length() > 0) {
            offerDraft.setDescription(description);
            log.info("Product: {} has been updated with new category: {}", offerDraft.getTitle(), description);
        }
    }
}
