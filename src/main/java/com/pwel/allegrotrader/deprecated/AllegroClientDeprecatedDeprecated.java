//package com.pwel.allegrotrader.deprecated;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pwel.allegrotrader.deprecated.dto.CategoryDto;
//import com.pwel.allegrotrader.deprecated.dto.ProductDto;
//import com.pwel.allegrotrader.deprecated.dto.PublishOfferDto;
//import com.pwel.allegrotrader.deprecated.dto.SearchCriteriaDto;
//import com.pwel.allegrotrader.deprecated.commons.SearchResultsDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONObject;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//class AllegroClientDeprecatedDeprecated implements AllegroInterfaceDeprecated {
//
//    private final AllegroRestClient allegroRestClient;
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public List<CategoryDto> getMainCategories() {
//        return mapToCategory(allegroRestClient.getMainCategories());
//    }
//
//    @Override
//    public List<CategoryDto> getSubCategories(int parentId) {
//        return mapToCategory(allegroRestClient.getSubcategories(parentId));
//    }
//
//    @Override
//    public List<CategoryDto> getMatchingCategories(ProductDto productDto) {
//        return mapToMatchingCategory(allegroRestClient.getMatchingCategories(productDto));
//    }
//
//    @Override
//    public List<SearchResultsDto> searchByParams(SearchCriteriaDto searchCriteriaDto) {
//        return mapToSearchResult(allegroRestClient.searchByRequest(searchCriteriaDto));
//    }
//
//    @Override
//    public ResponseEntity<JSONObject> publishOffer(PublishOfferDto publishOfferDto, String bearerUserToken) {
//        var response = allegroRestClient.publishOffer(
//                publishOfferDto,
//                bearerUserToken
//        );
//        return mapToPublishedOfferDto(response);
//    }
//
//    ResponseEntity<JSONObject> mapToPublishedOfferDto(ResponseEntity<JSONObject> responseEntity) {
//        // TODO check what's inside
//        //  Should be full offer object as a JSON
//        log.debug(String.valueOf(responseEntity));
//
//        // TODO map JSON response to PublishedOfferDto
//        // But adding to new PublishedOfferDto only fields that I need
//
//
//        return responseEntity;
//    }
//
//    JsonNode mapEntityToJsonNode(ResponseEntity<String> responseEntity, String bodyField) {
//        try {
//            JsonNode bodyRoot = objectMapper.readTree(responseEntity.getBody());
//            return bodyRoot.path(bodyField);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to map RestResponse into JsonNode" + e);
//        }
//    }
//
//    List<CategoryDto> mapToCategory(ResponseEntity<String> responseEntity) {
//        List<CategoryDto> categoryList = new ArrayList<>();
//        var jsonArray = mapEntityToJsonNode(responseEntity, "categories");
//        for (JsonNode node : jsonArray) {
//            categoryList.add(
//                    new CategoryDto(
//                            node.get("id").asInt(),
//                            node.get("name").asText()
//                    )
//            );
//        }
//        return categoryList;
//    }
//
//    List<CategoryDto> mapToMatchingCategory(ResponseEntity<String> responseEntity) {
//        List<CategoryDto> categoryList = new ArrayList<>();
//        var jsonArray = mapEntityToJsonNode(responseEntity, "matchingCategories");
//        for (JsonNode node : jsonArray) {
//            categoryList.add(
//                    new CategoryDto(
//                            node.get("id").asInt(),
//                            node.get("name").asText()
//                    )
//            );
//        }
//        return categoryList;
//    }
//
//    List<SearchResultsDto> mapToSearchResult(ResponseEntity<String> responseEntity) {
//        List<SearchResultsDto> resultsList = new ArrayList<>();
//        var jsonArrayRegular = mapEntityToJsonNode(responseEntity, "items").get("regular");
//        for (JsonNode node : jsonArrayRegular) {
//            resultsList.add(
//                    new SearchResultsDto(
//                            node.get("id").asInt(),
//                            node.get("name").asText(),
//                            false
//                    )
//            );
//        }
//        var jsonArrayPromoted = mapEntityToJsonNode(responseEntity, "items").get("promoted");
//        for (JsonNode node : jsonArrayPromoted) {
//            resultsList.add(
//                    new SearchResultsDto(
//                            node.get("id").asInt(),
//                            node.get("name").asText(),
//                            true
//                    )
//            );
//        }
//        return resultsList;
//    }
//}
