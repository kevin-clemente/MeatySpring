package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.Receita;
import org.springframework.data.repository.Repository;

public interface ReceitaRepository extends Repository<Receita, Integer> {
    public Iterable<Receita> findAll();
}
