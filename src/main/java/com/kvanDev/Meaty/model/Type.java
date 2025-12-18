package com.kvanDev.Meaty.model;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "type")
@Table
public class Type implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long typeId;
    private String typeName;
    @OneToMany( mappedBy = "type")
    @JsonBackReference
    private List<Ingredient> ingredients = new ArrayList<>();

    public Type() {
    }

    public Type(Long typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
