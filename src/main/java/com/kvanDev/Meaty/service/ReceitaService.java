package com.kvanDev.Meaty.service;

import com.kvanDev.Meaty.dao.ReceitaRepository;
import com.kvanDev.Meaty.model.Receita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {
    private final ReceitaRepository receitaRepository;

    @Autowired
    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Iterable<Receita> getAllReceitas(){
        return receitaRepository.findAll();
    }

}
