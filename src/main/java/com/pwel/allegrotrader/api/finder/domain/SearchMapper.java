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
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class SearchMapper {

    private static List<ItemDto> lastOfferList = null;

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

    public List<ItemDto> distinct(List<ItemDto> items) {
        var itemsIdList = toIdList(items);
        var lastOfferListIdList = toIdList(lastOfferList);

        if (lastOfferList != null) {
            log.info("Distincting lists: {} and {}", itemsIdList, lastOfferListIdList);
            var distinctList = filterDistinctOfLists(items, lastOfferList);
            lastOfferList = distinctList;
            return distinctList;
        }
        log.info("Not distincting lists: {} and {}", itemsIdList, lastOfferListIdList);
        lastOfferList = items;
        return items;
    }

    private List<ItemDto> filterDistinctOfLists(List<ItemDto> list1, List<ItemDto> list2) {
        return list1.stream()
                .filter(item -> !list2.contains(item.getId()))
                .toList();
    }

    private List<String> toIdList(List<ItemDto> items) {
        if (items != null) {
            return items.stream()
                    .map(ItemDto::getId)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return null;
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
