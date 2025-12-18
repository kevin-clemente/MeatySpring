package com.kvanDev.Meaty.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "recipe_ingredient")
public class RecipesIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    @JsonBackReference
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredientId")
    private Ingredient ingredient;

    @Column(name = "quantity")
    private String quantityG;

    public RecipesIngredients() {
    }

    public RecipesIngredients(Recipe recipe, Ingredient ingredient, String quantityG) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantityG = quantityG;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantityG() {
        return quantityG;
    }

    public void setQuantityG(String quantityG) {
        this.quantityG = quantityG;
    }
}
