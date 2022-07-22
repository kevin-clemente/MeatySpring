package com.kvanDev.Meaty;

import com.kvanDev.Meaty.dao.IngreditentRepository;
import com.kvanDev.Meaty.dao.TypeRepository;
import com.kvanDev.Meaty.model.Ingredient;
import com.kvanDev.Meaty.model.Type;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class meatyConfiguration  {
    @Bean
    CommandLineRunner commandLineRunner (TypeRepository repository, IngreditentRepository ingreditentRepository) {
        return args -> {

            ingreditentRepository.deleteAllInBatch();
            repository.deleteAllInBatch();

            Type peixe = new Type("peixe");
            Type carne = new Type("carne");
            Type especiaria = new Type("especiaria");

            repository.saveAll(List.of(peixe,carne,especiaria));

            Ingredient pernil = new Ingredient("pernil", carne);
            Ingredient robalo = new Ingredient("robalo", peixe);
            Ingredient dourada = new Ingredient("dourada", peixe);

            ingreditentRepository.saveAll(List.of(robalo,dourada,pernil));
            ingreditentRepository.flush();
            repository.flush();


        };
    }
}
