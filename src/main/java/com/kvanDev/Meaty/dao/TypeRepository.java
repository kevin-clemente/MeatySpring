package com.kvanDev.Meaty.dao;

import com.kvanDev.Meaty.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
}
