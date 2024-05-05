package com.pwel.allegrotrader.allegro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties("allegro")
public record AllegroProperties(

         // allegro api user credentials
         @NotBlank String clientId,
         @NotBlank String clientSecret,

         // allegro headers
         @NotBlank String allegroContentType,

         // allegro urls
         @NotBlank String urlWebsite,
         @NotBlank String urlApi,
         @NotBlank String categoriesPath,
         @NotBlank String offerSearchPath,

         // ALT urls
         @NotBlank String altUrlAuthRedirection
) {

}
