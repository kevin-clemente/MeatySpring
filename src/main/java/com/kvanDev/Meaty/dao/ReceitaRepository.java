package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Recipe, Integer> {
    public List<Recipe> findAll();

    public Recipe saveAndFlush(Recipe recipe);
}
