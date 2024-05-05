package com.pwel.allegrotrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.pwel.allegrotrader")
@ConfigurationPropertiesScan
@EnableScheduling
public class AllegroTraderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllegroTraderApplication.class, args);
    }
}
