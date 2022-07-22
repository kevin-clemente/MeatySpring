package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.Recipe;
import org.springframework.data.repository.Repository;

public interface ReceitaRepository extends Repository<Recipe, Integer> {
    public Iterable<Recipe> findAll();
}
