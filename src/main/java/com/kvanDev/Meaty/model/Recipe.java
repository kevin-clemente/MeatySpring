package com.kvanDev.Meaty.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity(name="recipe")
@Table
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    private String recipeName;
    private int recipePeople;
    private Timestamp recipeDateInsert;
    private String imagemUrl;
    @Column(length = 1000)
    private String description;
    @OneToMany(mappedBy = "recipe")
    private Set<RecipesIngredients> recipesIngredients = new HashSet<>();

    public Recipe() {
    }

    public Recipe(int recipeId, String recipe_name, int recipe_people, Timestamp recipe_date_insert, String imagem_url, Set<RecipesIngredients> recipesIngredients) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipePeople = recipePeople;
        this.recipeDateInsert = recipeDateInsert;
        this.imagemUrl = imagemUrl;
        this.recipesIngredients = recipesIngredients;
    }

    public int getRecipesId() {
        return recipeId;
    }

    public void setRecipesId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipesName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getRecipePeople() {
        return recipePeople;
    }

    public void setRecipePeople(int recipePeople) {
        this.recipePeople = recipePeople;
    }

    public Timestamp getRecipesDateInsert() {
        return recipeDateInsert;
    }

    public void setRecipesDateInsert(Timestamp recipesDateInsert) {
        this.recipeDateInsert = recipesDateInsert;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RecipesIngredients> getRecipesIngredients() {
        return recipesIngredients;
    }

    public void setRecipesIngredients(Set<RecipesIngredients> ingredient) {
        this.recipesIngredients = recipesIngredients;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "receita_id=" + recipeId +
                ", receita_nome='" + recipeName + '\'' +
                ", receita_pessoas=" + recipePeople +
                ", receita_date_insert=" + recipeDateInsert +
                ", imagem_url='" + imagemUrl + '\'' +
                ", ingredients='"+recipesIngredients+ '\'' +
                '}';
    }
}
