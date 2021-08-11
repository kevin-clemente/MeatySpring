package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.Recipes;
import org.springframework.data.repository.Repository;

public interface ReceitaRepository extends Repository<Recipes, Integer> {
    public Iterable<Recipes> findAll();
}
