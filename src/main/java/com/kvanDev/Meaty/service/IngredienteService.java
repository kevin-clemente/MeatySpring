package com.kvanDev.Meaty.service;

import com.kvanDev.Meaty.dao.IngreditenteRepository;
import com.kvanDev.Meaty.model.Ingrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    private final IngreditenteRepository ingreditenteRepository;

    @Autowired
    public IngredienteService(IngreditenteRepository ingreditenteRepository) {
        this.ingreditenteRepository = ingreditenteRepository;
    }

    public List<Ingrediente> getAllIngredientes(){
        return ingreditenteRepository.findAll();
    }

}
