package com.kvanDev.Meaty.service;

import com.kvanDev.Meaty.dao.IngreditentRepository;
import com.kvanDev.Meaty.model.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {

    private final IngreditentRepository ingreditentRepository;

    @Autowired
    public IngredientsService(IngreditentRepository ingreditentRepository) {
        this.ingreditentRepository = ingreditentRepository;
    }

    public List<Ingredients> getAllIngredients(){
        return ingreditentRepository.findAll();
    }

}