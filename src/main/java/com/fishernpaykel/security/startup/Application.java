package com.fishernpaykel.security.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fishernpaykel.security.business.SecurityValidationComponent;
import com.fishernpaykel.security.service.SecurityValidationService;

/**
 * Main Class for the Spring boot.
 *
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.fishernpaykel.security")
public class Application 
{
    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(Application.class, args);
    }
}
