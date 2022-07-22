package com.kvanDev.Meaty.service;

import com.kvanDev.Meaty.dao.TypeRepository;
import com.kvanDev.Meaty.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAllType(){return typeRepository.findAll();}
}
