package com.pwel.allegrotrader.allegro.domain.search.response.offer;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Regular {
    String id;
    String name;
    Seller seller;
    Promotion promotion;
    Delivery delivery;
    ArrayList<Image> images;
    SellingMode sellingMode;
    Stock stock;
    Vendor vendor;
    Category category;
    Publication publication;
}
