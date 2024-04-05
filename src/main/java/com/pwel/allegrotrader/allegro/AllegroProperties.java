package com.pwel.allegrotrader.allegro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties("allegro")
record AllegroProperties(
         @NotBlank String hostWebsite,
         @NotBlank String hostApi,
         @NotBlank String mainCategoriesPath,
         @NotBlank String allegroHeadersV1
) {

}
