package com.pwel.allegrotrader.api.offer.infrastructure;

import com.pwel.allegrotrader.api.offer.domain.PublishedOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublishedOfferRepository extends JpaRepository<PublishedOffer, Long> {

    Optional<PublishedOffer> findPublishedOfferByTitle(String title);

    @Query("SELECT o FROM PublishedOffer o WHERE lower(o.title) LIKE lower(concat('%',:title,'%'))")
    List<PublishedOffer> findPublishedOffersByTitle(String title);

}
