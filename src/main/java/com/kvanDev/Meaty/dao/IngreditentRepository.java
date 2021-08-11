package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.Ingredients;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IngreditenteRepository extends Repository<Ingredients,Integer> {
    List<Ingredients> findAll();
    Ingredients findById(int id);
    Ingredients findByName(String name);
    Ingredients findByType(String type);
}
