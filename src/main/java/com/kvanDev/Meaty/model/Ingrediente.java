package com.kvanDev.Meaty.model;

import javax.persistence.*;

@Entity(name = "ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ingrediente_id;
    @Column(name = "ingrediente_nome")
    private String name;
    @Column(name="ingrediente_tipo")
    private String tipo;

    public int getIngrediente_id() {
        return ingrediente_id;
    }

    public void setIngrediente_id(int ingrediente_id) {
        this.ingrediente_id = ingrediente_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
