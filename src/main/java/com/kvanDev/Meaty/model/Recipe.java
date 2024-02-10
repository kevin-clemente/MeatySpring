package com.kvanDev.Meaty.model;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity(name="recipes")
@Table
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipe_id;
    private String recipe_name;
    private int recipe_people;
    private Timestamp recipe_date_insert;
    private String imagem_url;
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable( name = "recipe_ingredient",
            joinColumns = { @JoinColumn(name = "recipe_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id")}
    )
    private Set<Ingredient> ingredient = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String recipe_name, int recipe_people, Timestamp recipe_date_insert, String imagem_url) {
        this.recipe_name = recipe_name;
        this.recipe_people = recipe_people;
        this.recipe_date_insert = recipe_date_insert;
        this.imagem_url = imagem_url;
    }

    public Recipe(int recipe_id, String recipe_name, int recipe_people, Timestamp recipe_date_insert, String imagem_url, Set<Ingredient> ingredient) {
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
        this.recipe_people = recipe_people;
        this.recipe_date_insert = recipe_date_insert;
        this.imagem_url = imagem_url;
        this.ingredient = ingredient;
    }

    public int getRecipes_id() {
        return recipe_id;
    }

    public void setRecipes_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipes_name() {
        return recipe_name;
    }

    public void setRecipe_name (String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public int getRecipe_people() {
        return recipe_people;
    }

    public void setRecipe_people(int recipe_people) {
        this.recipe_people = recipe_people;
    }

    public Timestamp getRecipes_date_insert() {
        return recipe_date_insert;
    }

    public void setRecipes_date_insert(Timestamp recipes_date_insert) {
        this.recipe_date_insert = recipes_date_insert;
    }

    public String getImagem_url() {
        return imagem_url;
    }

    public void setImagem_url(String imagem_url) {
        this.imagem_url = imagem_url;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "receita_id=" + recipe_id +
                ", receita_nome='" + recipe_name + '\'' +
                ", receita_pessoas=" + recipe_people +
                ", receita_date_insert=" + recipe_date_insert +
                ", imagem_url='" + imagem_url + '\'' +
                '}';
    }
}
