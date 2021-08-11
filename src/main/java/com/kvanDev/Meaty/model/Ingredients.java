package com.kvanDev.Meaty.model;

import javax.persistence.*;

@Entity(name = "ingrediente")
@Table
public class Ingredients {
    @Id
    @SequenceGenerator(
            name = "ingredientSequence",
            sequenceName = "ingredientSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ingredientSequence"
    )
    private Long id;
    private String name;
    private String type;

    public Long getIngrediente_id() {
        return id;
    }

    public void setIngrediente_id(Long id) {
        this.id = id;
    }

    public Ingredients(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Ingredients(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return type;
    }

    public void setTipo(String type) {
        this.type = type;
    }
}
