package com.kvanDev.Meaty.api;

import com.kvanDev.Meaty.model.Ingrediente;
import com.kvanDev.Meaty.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class IngredienteController {
    private final IngredienteService ingredienteService;

    @Autowired
    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Ingrediente> getAllIngredientes(){
        return ingredienteService.getAllIngredientes();
    }

}

