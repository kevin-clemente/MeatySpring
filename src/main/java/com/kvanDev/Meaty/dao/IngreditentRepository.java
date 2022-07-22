package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IngreditentRepository extends JpaRepository<Ingredient,Integer> {
    public List<Ingredient> findAll();
    public void deleteAllInBatch();
    public Ingredient save(Ingredient ingredient);
}
