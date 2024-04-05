package com.pwel.allegrotrader.api.offer.infrastructure;

import com.pwel.allegrotrader.api.offer.domain.OfferDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferDraftRepository extends JpaRepository<OfferDraft, Long> {

    Optional<OfferDraft> findOfferDraftByTitle(String title);

    @Query("SELECT o FROM OfferDraft o WHERE lower(o.title) LIKE lower(concat('%',:title,'%'))")
    List<OfferDraft> findOfferDraftsByTitle(String title);
}
