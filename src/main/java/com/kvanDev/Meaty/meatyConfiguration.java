package com.kvanDev.Meaty;

import com.kvanDev.Meaty.service.RecipeDataLoaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class meatyConfiguration  {
    @Bean
    CommandLineRunner commandLineRunner(RecipeDataLoaderService recipeDataLoaderService) {
        return args -> {
            try {
                System.out.println("=== Starting data loading from JSON ===");

                // Clear existing data
                recipeDataLoaderService.clearAllData();

                // Load data from JSON file in resources
                recipeDataLoaderService.loadRecipesFromJson("recipes_data.json");

                System.out.println("=== Data loading completed successfully ===");
            } catch (Exception e) {
                System.err.println("Error loading recipe data: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
