package org.wora;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wora.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        // Initialize the Spring context with your configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Optionally, you can retrieve a bean to trigger any initialization or operations
        // CyclistService cyclistService = context.getBean(CyclistService.class);
        // cyclistService.someMethodToInitialize(); // This method can initialize the database if needed

        System.out.println("Application started. Check the database for created tables.");
    }
}
