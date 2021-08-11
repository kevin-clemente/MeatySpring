package com.kvanDev.Meaty.api;

import com.kvanDev.Meaty.model.Recipes;
import com.kvanDev.Meaty.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/meaty")
public class RecipesController {
    private final RecipesService recipesService;
    @Autowired
    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping(path = "/getAllRecipes")
    public @ResponseBody Iterable<Recipes> getAll(){
        return recipesService.getAllRecipes();
    }

}
