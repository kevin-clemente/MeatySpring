package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.RecipesIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesIngredientsRepository extends JpaRepository<RecipesIngredients,Integer> {

}
