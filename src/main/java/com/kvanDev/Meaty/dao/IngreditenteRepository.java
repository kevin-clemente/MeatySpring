package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.Ingrediente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IngreditenteRepository extends Repository<Ingrediente,Integer> {
    List<Ingrediente> findAll();
    Ingrediente findById(int id);
    Ingrediente findByName(String name);
    Ingrediente findByTipo(String tipo);
}
