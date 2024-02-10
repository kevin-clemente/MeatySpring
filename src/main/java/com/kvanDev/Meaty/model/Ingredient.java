package com.kvanDev.Meaty.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ingredient_id;
    private String ingredient_name;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL, mappedBy = "ingredient")
    private Set<Recipe> recipe = new HashSet<>();

    public Long getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(Long id) {
        this.ingredient_id = id;
    }

    public Ingredient() {
    }

    public Ingredient(Long ingredient_id, String ingredient_name, Type type) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
        this.type = type;
    }

    public Ingredient(String ingredient_name, Type type) {
        this.ingredient_name = ingredient_name;
        this.type = type;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
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
        return ingredient_id.equals(that.ingredient_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient_id);
    }
}
