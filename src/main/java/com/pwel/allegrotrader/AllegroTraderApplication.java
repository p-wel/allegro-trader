package com.pwel.allegrotrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.pwel.allegrotrader")
@ConfigurationPropertiesScan
public class AllegroTraderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllegroTraderApplication.class, args);
    }
}
