package com.pwel.allegrotrader.allegro.domain.search.response.offer;

import java.util.ArrayList;
import java.util.Date;

public record OfferSearchResponse(Items items,
                                  Categories categories,
                                  ArrayList<Filter> filters,
                                  SearchMeta searchMeta,
                                  ArrayList<Sort> sort) {
}

class Categories {
    ArrayList<Subcategory> subcategories;
    ArrayList<Path> path;
}

class Category {
    String id;
}

class Delivery {
    boolean availableForFree;
    LowestPrice lowestPrice;
}

class Filter {
    String id;
    String type;
    String name;
    ArrayList<Value> values;
    int minValue;
    int maxValue;
    String unit;
}

class FixedPrice {
    String amount;
    String currency;
}

class Image {
    String url;
}

class Items {
    ArrayList<Promoted> promoted;
    ArrayList<Regular> regular;
}

class LowestPrice {
    String amount;
    String currency;
}

class Path {
    String id;
    String name;
}

class Price {
    String amount;
    String currency;
}

class Promoted {
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

class Promotion {
    boolean emphasized;
    boolean bold;
    boolean highlight;
}

class Publication {
    Date endingAt;
}

class Regular {
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

class SearchMeta {
    int availableCount;
    int totalCount;
    boolean fallback;
}

class Seller {
    String id;
    String login;
    boolean company;
    boolean superSeller;
}

class SellingMode {
    String format;
    Price price;
    FixedPrice fixedPrice;
    int popularity;
    String popularityRange;
    int bidCount;
}

class Sort {
    String value;
    String name;
    String order;
    boolean selected;
}

class Stock {
    String unit;
    int available;
}

class Subcategory {
    String id;
    String name;
    int count;
}

class Value {
    String name;
    String value;
    String idSuffix;
    int count;
    boolean selected;
}

class Vendor {
    String id;
    String url;
}

