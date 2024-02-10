package com.kvanDev.Meaty.model;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Type implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long type_id;
    private String type_name;
    @OneToMany( mappedBy = "type")
    @JsonBackReference
    private List<Ingredient> ingredients = new ArrayList<>();

    public Type() {
    }

    public Type(Long type_id, String type_name) {
        this.type_id = type_id;
        this.type_name = type_name;
    }

    public Type(String type_name) {
        this.type_name = type_name;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
