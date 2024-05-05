package com.pwel.allegrotrader.allegro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties("allegro")
public record AllegroProperties(
         @NotBlank String urlWebsite,
         @NotBlank String urlApi,
         @NotBlank String clientId,
         @NotBlank String clientSecret,

         @NotBlank String allegroContentType,

         @NotBlank String categoriesPath
) {

}
