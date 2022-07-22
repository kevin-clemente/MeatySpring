package com.kvanDev.Meaty.api;

import com.kvanDev.Meaty.model.Ingredient;
import com.kvanDev.Meaty.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/ingredientes")
public class IngredientController {
    private final IngredientsService ingredientsService;

    @Autowired
    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Ingredient> getAllIngredients(){
        return ingredientsService.getAllIngredients();
    }

    @PostMapping(path = "/create")
    public @ResponseBody Ingredient createIngredient(@RequestBody Ingredient ingredient){
        return ingredientsService.saveIngredient(ingredient);
    }

}

