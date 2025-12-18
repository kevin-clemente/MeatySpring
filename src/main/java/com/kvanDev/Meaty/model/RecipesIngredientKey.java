package com.kvanDev.Meaty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
public class RecipesIngredientKey implements Serializable {
    @Setter
    @Column(name = "recipeId")
    Long recipeId;
    @Setter
    @Column(name= "ingredientId")
    Long ingredienteId;

}
