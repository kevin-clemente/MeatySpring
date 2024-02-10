package com.kvanDev.Meaty.api;

import com.kvanDev.Meaty.model.Recipe;
import com.kvanDev.Meaty.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/meaty")
public class RecipesController {
    private final RecipesService recipesService;

    @Autowired
    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping(path = "/getAllRecipes")
    public @ResponseBody Iterable<Recipe> getAll() {
        return recipesService.getAllRecipes();
    }

    @PostMapping(path = "/createRecipe")
    public @ResponseBody Recipe createRecipe(@RequestHeader("userId") int userId, @RequestBody Recipe recipe) {
        recipe.setRecipe_people(userId);
        return recipesService.createRecipe(recipe);
    }
}
