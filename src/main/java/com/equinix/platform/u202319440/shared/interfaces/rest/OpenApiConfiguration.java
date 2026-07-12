package com.equinix.platform.u202319440.shared.interfaces.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for the DCMonitor Platform API.
 *
 * @author Dhilsen Armil Mallqui Vilca
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI dcMonitorOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("DCMonitor Platform API")
                        .description("REST API for Equinix operational monitoring - Assignments and Tracking")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Dhilsen Armil Mallqui Vilca")));
    }
}
