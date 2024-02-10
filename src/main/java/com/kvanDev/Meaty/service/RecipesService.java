package com.kvanDev.Meaty.service;

import com.kvanDev.Meaty.dao.ReceitaRepository;
import com.kvanDev.Meaty.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipesService {
    private final ReceitaRepository receitaRepository;

    @Autowired
    public RecipesService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Iterable<Recipe> getAllRecipes(){
        return receitaRepository.findAll();
    }

    public Recipe createRecipe(Recipe recipe){
        return receitaRepository.saveAndFlush(recipe);
    }

}
