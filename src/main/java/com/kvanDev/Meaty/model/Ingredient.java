package com.kvanDev.Meaty.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "ingredient")
@Table
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ingredientId;
    private String ingredientName;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @OneToMany(mappedBy = "ingredient")
    @JsonBackReference
    private Set<RecipesIngredients> recipesIngredients = new HashSet<>();

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long id) {
        this.ingredientId = id;
    }

    public Ingredient() {
    }

    public Ingredient(Long ingredientId, String ingredientName, Type type) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.type = type;
    }

    public Ingredient(String ingredientName, Type type) {
        this.ingredientName = ingredientName;
        this.type = type;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return ingredientId.equals(that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId);
    }
}
