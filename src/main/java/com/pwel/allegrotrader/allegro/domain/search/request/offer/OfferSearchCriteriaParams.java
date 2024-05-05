package com.pwel.allegrotrader.allegro.domain.search.request.offer;

import lombok.Builder;

@Builder
public record OfferSearchCriteriaParams(String categoryId,
                                        String phrase,
                                        String sellerId,
                                        String sellerLogin,
                                        String marketplaceId,
                                        String shippingCountry,
                                        String currency,
                                        String searchMode,
                                        int offset,
                                        int limit,
                                        String sort,
                                        String include,
                                        boolean fallback,
                                        DynamicFilter dynamicFilters) {

}
