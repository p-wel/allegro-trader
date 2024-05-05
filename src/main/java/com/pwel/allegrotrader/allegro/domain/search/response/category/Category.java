package com.pwel.allegrotrader.allegro.domain.search.response.category;


public record Category(String id,
                       boolean leaf,
                       String name,
                       Options options,
                       Parent parent) {
}

class Options {
    boolean advertisement;
    boolean advertisementPriceOptional;
    boolean variantsByColorPatternAllowed;
    boolean offersWithProductPublicationEnabled;
    boolean productCreationEnabled;
    boolean customParametersEnabled;
    boolean sellerCanRequirePurchaseComments;
}

class Parent {
    String id;
}
