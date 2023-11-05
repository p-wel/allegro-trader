package com.pwel.allegrotrader.allegro;

import com.pwel.allegrotrader.allegro.dto.PublishOfferDto;
import org.json.JSONArray;
import org.json.JSONObject;

class JsonBodyFactoryForAllegroRequest {
    static JSONObject createPublishOfferBodyWithGTIN(PublishOfferDto publishOfferDto) {
        JSONObject product = new JSONObject();
        product.put("id", publishOfferDto.getGtinId());
        product.put("idType", "GTIN");

        JSONObject productSet = new JSONObject();
        productSet.put("product", product);

        JSONArray productSetArray = new JSONArray();
        productSetArray.put(productSet);

        JSONObject price = new JSONObject();
        price.put("amount", publishOfferDto.getPrice());
        price.put("currency", publishOfferDto.getCurrency());

        JSONObject sellingMode = new JSONObject();
        sellingMode.put("price", price);

        JSONObject stock = new JSONObject();
        stock.put("available", publishOfferDto.getQuantity());

        JSONObject requestBody = new JSONObject();
        requestBody.put("productSet", productSetArray);
        requestBody.put("sellingMode", sellingMode);
        requestBody.put("stock", stock);

        return requestBody;
    }

    static JSONObject createPublishOfferBodyWithSpecificId(PublishOfferDto publishOfferDto) {
        JSONArray valuesIds = new JSONArray();
        valuesIds.put(publishOfferDto.getParametersValuesId());

        JSONObject parameters = new JSONObject();
        parameters.put("id", publishOfferDto.getParametersId());
        parameters.put("valuesIds", valuesIds);

        JSONArray parametersArray = new JSONArray();
        parametersArray.put(parameters);

        JSONObject product = new JSONObject();
        product.put("id", publishOfferDto.getGtinId()); // get specific Id, not GtinId
        product.put("parameters", parametersArray);

        JSONObject productObject = new JSONObject();
        productObject.put("product", product);

        JSONArray productSet = new JSONArray();
        productSet.put(productObject);

        JSONObject price = new JSONObject();
        price.put("amount", publishOfferDto.getPrice());
        price.put("currency", publishOfferDto.getCurrency());

        JSONObject sellingMode = new JSONObject();
        sellingMode.put("price", price);

        JSONObject stock = new JSONObject();
        stock.put("available", publishOfferDto.getQuantity());

        JSONObject requestBody = new JSONObject();
        requestBody.put("productSet", productSet);
        requestBody.put("sellingMode", sellingMode);
        requestBody.put("stock", stock);

        return requestBody;
    }

}
