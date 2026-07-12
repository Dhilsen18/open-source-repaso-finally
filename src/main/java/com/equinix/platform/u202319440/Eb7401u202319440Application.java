package com.equinix.platform.u202319440;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main entry point for the DCMonitor Platform Spring Boot application.
 *
 * @author Dhilsen Armil Mallqui Vilca
 */
@EnableJpaAuditing
@SpringBootApplication
public class Eb7401u202319440Application {

    public static void main(String[] args) {
        SpringApplication.run(Eb7401u202319440Application.class, args);
    }
}
