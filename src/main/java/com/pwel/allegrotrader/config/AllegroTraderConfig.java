package com.pwel.allegrotrader.config;

import com.pwel.allegrotrader.finder.domain.SearchCriteria;
import com.pwel.allegrotrader.finder.infrastructure.SearchRepository;
import com.pwel.allegrotrader.offer.domain.OfferDraft;
import com.pwel.allegrotrader.offer.domain.PublishedOffer;
import com.pwel.allegrotrader.offer.domain.Status;
import com.pwel.allegrotrader.offer.infrastructure.OfferDraftRepository;
import com.pwel.allegrotrader.offer.infrastructure.PublishedOfferRepository;
import com.pwel.allegrotrader.product.domain.Product;
import com.pwel.allegrotrader.product.infrastructure.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class AllegroTraderConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            SearchRepository searchRepository,
            ProductRepository productRepository,
            OfferDraftRepository offerDraftRepository,
            PublishedOfferRepository publishedOfferRepository
    ) {
        return args -> {

            Product szybaPrzedniaToyota = new Product(
                    "Szyba przednia Toyota",
                    "Motoryzacja",
                    "https://media.istockphoto.com/id/1150931120/pl/zdj%C4%99cie/ilustracja-3d-og%C3%B3lnego-kompaktowego-bia%C5%82ego-samochodu-widok-z-przodu.jpg?s=2048x2048&w=is&k=20&c=MAaPrTdvk4qkiDA5Bu3oU2BedxetsuAPsr372KZg73Q="
            );
            Product filtrPaliwaToyota = new Product(
                    "Filtr paliwa Toyota",
                    "Motoryzacja",
                    "https://media.istockphoto.com/id/1136053255/pl/zdj%C4%99cie/r%C4%99czne-nape%C5%82nianie-samochodu-paliwem-na-stacji-tankowania.jpg?s=2048x2048&w=is&k=20&c=haA3vUWI0d5PhmMgS3qPvn8RV5NTzECLQk94UpCL7uE="

            );

            Product drzwiFord = new Product(
                    "Drzwi Ford",
                    "Motoryzacja",
                    "https://media.istockphoto.com/id/1377713917/pl/zdj%C4%99cie/otwarte-drzwi-samochodu-osobowego.jpg?s=2048x2048&w=is&k=20&c=qHPTrNAhWBddIa2l0kzPCm7E0Vqz6dLDuVo8TYNI3QY="
            );

            Product lusterkoVolkswagen = new Product(
                    "Lusterko Volkswagen",
                    "Motoryzacja",
                    "https://media.istockphoto.com/id/487091534/pl/zdj%C4%99cie/zach%C3%B3d-s%C5%82o%C5%84ca-w-lusterko-wsteczne.jpg?s=2048x2048&w=is&k=20&c=taSBtxXrHdCrrMykNEooOU95u4CUug7rxqt3Msn8czs="
            );

            OfferDraft draft1 = new OfferDraft(
                    1L,
                    "Nazwa draftu oferty 1",
                    "Opis draftu oferty",
                    new BigDecimal("100.99")
            );

            OfferDraft draft2 = new OfferDraft(
                    2L,
                    "Nazwa draftu oferty 2",
                    "Opis draftu oferty",
                    new BigDecimal("100.99")
            );

            OfferDraft draft3 = new OfferDraft(
                    3L,
                    "Nazwa draftu oferty 3",
                    "Opis draftu oferty",
                    new BigDecimal("100.99")
            );

            OfferDraft draft4 = new OfferDraft(
                    4L,
                    "Nazwa draftu oferty 4",
                    "Opis draftu oferty",
                    new BigDecimal("100.99")
            );

            SearchCriteria toyota = new SearchCriteria(
                    "toyota",
                    new BigDecimal("20.000"),
                    new BigDecimal("50.000"),
                    "Motoryzacja",
                    "Warszawa"
            );

            SearchCriteria ford = new SearchCriteria(
                    "ford",
                    new BigDecimal("10.000"),
                    new BigDecimal("30.000"),
                    "Motoryzacja",
                    "Poznań"
            );

            SearchCriteria mercedes = new SearchCriteria(
                    "mercedes",
                    new BigDecimal("25.000"),
                    new BigDecimal("55.000"),
                    "Motoryzacja",
                    "Kraków"
            );

            PublishedOffer publishedOffer1 = new PublishedOffer(
                    990L,
                    222L,
                    "Tytuł opublikowanej oferty",
                    "Opis opublikowanej oferty",
                    new BigDecimal("111.00"),
                    Status.ACTIVE
            );

            productRepository.saveAll(
                    List.of(szybaPrzedniaToyota, filtrPaliwaToyota, drzwiFord, lusterkoVolkswagen)
            );

            offerDraftRepository.saveAll(
                    List.of(draft1, draft2, draft3, draft4)
            );

            searchRepository.saveAll(
                    List.of(toyota, ford, mercedes)
            );
            publishedOfferRepository.saveAll(
                    List.of(publishedOffer1)
            );
        };
    }
}
