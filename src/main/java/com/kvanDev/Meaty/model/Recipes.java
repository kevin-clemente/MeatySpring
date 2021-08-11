package com.kvanDev.Meaty.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="receita")
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int receita_id;
    @Column(name="receita_nome")
    private String receita_nome;
    @Column(name = "receita_pessoas")
    private int receita_pessoas;
    @Column(name="receita_data_insert")
    private Timestamp receita_data_insert;
    @Column(name="imagem_url")
    private String imagem_url;

    public int getReceita_id() {
        return receita_id;
    }

    public void setReceita_id(int receita_id) {
        this.receita_id = receita_id;
    }

    public String getReceita_nome() {
        return receita_nome;
    }

    public void setReceita_nome(String receita_nome) {
        this.receita_nome = receita_nome;
    }

    public int getReceita_pessoas() {
        return receita_pessoas;
    }

    public void setReceita_pessoas(int receita_pessoas) {
        this.receita_pessoas = receita_pessoas;
    }

    public Timestamp getReceita_data_insert() {
        return receita_data_insert;
    }

    public void setReceita_data_insert(Timestamp receita_data_insert) {
        this.receita_data_insert = receita_data_insert;
    }

    public String getImagem_url() {
        return imagem_url;
    }

    public void setImagem_url(String imagem_url) {
        this.imagem_url = imagem_url;
    }
}
