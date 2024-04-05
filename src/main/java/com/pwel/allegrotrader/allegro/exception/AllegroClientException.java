package com.pwel.allegrotrader.allegro.exception;

import com.pwel.allegrotrader.api.product.web.exception.ApiException;

public class AllegroClientException extends ApiException {

    public AllegroClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
