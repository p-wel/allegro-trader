package com.pwel.allegrotrader.api.finder.domain;

import com.pwel.allegrotrader.allegro.domain.search.response.category.CategoriesResponse;
import com.pwel.allegrotrader.allegro.domain.search.response.offer.Promoted;
import com.pwel.allegrotrader.allegro.domain.search.response.offer.Regular;
import com.pwel.allegrotrader.api.finder.model.offer.FixedPrice;
import com.pwel.allegrotrader.api.finder.model.offer.ItemDto;
import com.pwel.allegrotrader.allegro.domain.search.response.offer.OfferSearchResponse;
import com.pwel.allegrotrader.api.finder.model.CategoryDto;
import com.pwel.allegrotrader.api.finder.model.offer.Price;
import com.pwel.allegrotrader.api.finder.model.offer.SellingMode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SearchMapper {

    public List<CategoryDto> toCategoryList(CategoriesResponse response) {
        var list = new ArrayList<CategoryDto>();
        response.categories().forEach(category -> list.add(
                CategoryDto.builder()
                        .id(category.id())
                        .name(category.name())
                        .build())
        );
        return list;
    }

    public List<ItemDto> toOfferList(OfferSearchResponse response) {
        var list = new ArrayList<ItemDto>();
        response.items().getPromoted().forEach(promoted ->
                list.add(retrieveItemDto(promoted))
        );
        response.items().getRegular().forEach(regular ->
                list.add(retrieveItemDto(regular)));
        return list;
    }

    private ItemDto retrieveItemDto(Promoted promoted) {
        var sellingMode = retrieveSellingMode(promoted);
        return ItemDto.builder()
                .id(promoted.getId())
                .name(promoted.getName())
                .sellingMode(sellingMode)
                .build();
    }

    private ItemDto retrieveItemDto(Regular regular) {
        var sellingMode = retrieveSellingMode(regular);
        return ItemDto.builder()
                .id(regular.getId())
                .name(regular.getName())
                .sellingMode(sellingMode)
                .build();
    }

    private SellingMode retrieveSellingMode(Promoted promoted) {
        var price = retrievePrice(promoted);
        var fixedPrice = retrieveFixedPrice(promoted);
        return SellingMode.builder()
                .format(promoted.getSellingMode().getFormat())
                .price(price)
                .fixedPrice(fixedPrice)
                .build();
    }

    private SellingMode retrieveSellingMode(Regular regular) {
        var price = retrievePrice(regular);
        var fixedPrice = retrieveFixedPrice(regular);
        return SellingMode.builder()
                .format(regular.getSellingMode().getFormat())
                .price(price)
                .fixedPrice(fixedPrice)
                .build();
    }

    private Price retrievePrice(Promoted promoted) {
        return Price.builder()
                .amount(promoted.getSellingMode().getPrice().getAmount())
                .currency(promoted.getSellingMode().getPrice().getCurrency())
                .build();
    }

    private Price retrievePrice(Regular regular) {
        return Price.builder()
                .amount(regular.getSellingMode().getPrice().getAmount())
                .currency(regular.getSellingMode().getPrice().getCurrency())
                .build();
    }

    private FixedPrice retrieveFixedPrice(Promoted promoted) {
        return FixedPrice.builder()
                .amount(promoted.getSellingMode().getPrice().getAmount())
                .currency(promoted.getSellingMode().getPrice().getCurrency())
                .build();
    }

    private FixedPrice retrieveFixedPrice(Regular regular) {
        return FixedPrice.builder()
                .amount(regular.getSellingMode().getPrice().getAmount())
                .currency(regular.getSellingMode().getPrice().getCurrency())
                .build();
    }
}
