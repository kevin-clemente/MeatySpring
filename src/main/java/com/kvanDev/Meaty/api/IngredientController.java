package com.kvanDev.Meaty.api;

import com.kvanDev.Meaty.model.Ingredients;
import com.kvanDev.Meaty.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class IngredienteController {
    private final IngredientsService ingredientsService;

    @Autowired
    public IngredienteController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Ingredients> getAllIngredientes(){
        return ingredientsService.getAllIngredientes();
    }

}

