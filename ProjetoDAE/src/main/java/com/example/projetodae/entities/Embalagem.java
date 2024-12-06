package com.example.projetodae.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class Embalagem {

    @Id
    @NotNull
    private int id;

    @NotNull
    private int idEncomenda;

    public Embalagem() {
    }

    public Embalagem(int id, int idEncomenda) {
        this.id = id;
        this.idEncomenda = idEncomenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }
}
