package com.kvanDev.Meaty.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kvanDev.Meaty.dao.IngreditentRepository;
import com.kvanDev.Meaty.dao.ReceitaRepository;
import com.kvanDev.Meaty.dao.RecipesIngredientsRepository;
import com.kvanDev.Meaty.dao.TypeRepository;
import com.kvanDev.Meaty.model.Ingredient;
import com.kvanDev.Meaty.model.Recipe;
import com.kvanDev.Meaty.model.RecipesIngredients;
import com.kvanDev.Meaty.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class RecipeDataLoaderService {

    private final TypeRepository typeRepository;
    private final IngreditentRepository ingredientRepository;
    private final ReceitaRepository recipeRepository;
    private final RecipesIngredientsRepository recipesIngredientsRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public RecipeDataLoaderService(
            TypeRepository typeRepository,
            IngreditentRepository ingredientRepository,
            ReceitaRepository recipeRepository,
            RecipesIngredientsRepository recipesIngredientsRepository) {
        this.typeRepository = typeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipesIngredientsRepository = recipesIngredientsRepository;
        this.objectMapper = new ObjectMapper();
    }

    @Transactional
    public void loadRecipesFromJson(String jsonFilePath) throws IOException {
        // Read JSON file from classpath or filesystem
        Resource resource = new ClassPathResource(jsonFilePath);
        JsonNode rootNode = objectMapper.readTree(resource.getInputStream());

        // Maps to store entities by name for quick lookup
        Map<String, Type> typeMap = new HashMap<>();
        Map<String, Ingredient> ingredientMap = new HashMap<>();

        // 1. Load Types
        System.out.println("Loading types...");
        JsonNode typesNode = rootNode.get("types");
        if (typesNode != null && typesNode.isArray()) {
            for (JsonNode typeNode : typesNode) {
                String typeName = typeNode.get("type_name").asText();
                Type type = new Type(typeName);
                type = typeRepository.save(type);
                typeMap.put(typeName, type);
                System.out.println("Loaded type: " + typeName);
            }
        }
        typeRepository.flush();

        // 2. Load Ingredients
        System.out.println("Loading ingredients...");
        JsonNode ingredientsNode = rootNode.get("ingredients");
        if (ingredientsNode != null && ingredientsNode.isArray()) {
            for (JsonNode ingredientNode : ingredientsNode) {
                String ingredientName = ingredientNode.get("ingredient_name").asText();
                String typeName = ingredientNode.get("type_name").asText();

                Type type = typeMap.get(typeName);
                if (type != null) {
                    Ingredient ingredient = new Ingredient(ingredientName, type);
                    ingredient = ingredientRepository.save(ingredient);
                    ingredientMap.put(ingredientName, ingredient);
                    System.out.println("Loaded ingredient: " + ingredientName + " (" + typeName + ")");
                } else {
                    System.err.println("Type not found for ingredient: " + ingredientName + " - " + typeName);
                }
            }
        }
        ingredientRepository.flush();

        // 3. Load Recipes
        System.out.println("Loading recipes...");
        JsonNode recipesNode = rootNode.get("recipes");
        if (recipesNode != null && recipesNode.isArray()) {
            int count = 0;
            for (JsonNode recipeNode : recipesNode) {
                String recipeName = recipeNode.get("recipe_name").asText();
                int recipePeople = recipeNode.get("recipe_people").asInt();
                String imageUrl = recipeNode.get("imagem_url").asText();
                String description = recipeNode.has("description") ? recipeNode.get("description").asText() : "";

                // Create recipe entity
                Recipe recipe = new Recipe();
                recipe.setRecipeName(recipeName);
                recipe.setRecipePeople(recipePeople);
                recipe.setRecipesDateInsert(Timestamp.from(Instant.now()));
                recipe.setImagemUrl(imageUrl);
                recipe.setDescription(description);

                // Save recipe first to get the ID
                recipe = recipeRepository.save(recipe);

                // Create RecipesIngredients entries with quantities
                JsonNode recipeIngredientsArray = recipeNode.get("recipe_ingredients");
                if (recipeIngredientsArray != null && recipeIngredientsArray.isArray()) {
                    for (JsonNode recipeIngredientNode : recipeIngredientsArray) {
                        String ingredientName = recipeIngredientNode.get("ingredient_name").asText();
                        String quantity = recipeIngredientNode.get("quantity").asText();

                        Ingredient ingredient = ingredientMap.get(ingredientName);
                        if (ingredient != null) {
                            RecipesIngredients recipeIngredient = new RecipesIngredients(recipe, ingredient, quantity);
                            recipesIngredientsRepository.save(recipeIngredient);
                        } else {
                            System.err.println("Ingredient not found: " + ingredientName + " for recipe: " + recipeName);
                        }
                    }
                }

                count++;
                System.out.println("Loaded recipe " + count + ": " + recipeName);
            }
        }
        recipeRepository.flush();
        recipesIngredientsRepository.flush();

        System.out.println("Data loading completed!");
        System.out.println("Total types: " + typeMap.size());
        System.out.println("Total ingredients: " + ingredientMap.size());
        System.out.println("Total recipes: " + recipeRepository.count());
    }

    @Transactional
    public void clearAllData() {
        System.out.println("Clearing all existing data...");
        recipesIngredientsRepository.deleteAllInBatch();
        recipeRepository.deleteAllInBatch();
        ingredientRepository.deleteAllInBatch();
        typeRepository.deleteAllInBatch();
        System.out.println("All data cleared.");
    }
}
