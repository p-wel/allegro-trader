package com.pwel.allegrotrader.allegro.exception;

import com.pwel.allegrotrader.api.product.web.exception.ApiException;

public class AllegroMappingException extends ApiException {

    public AllegroMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
