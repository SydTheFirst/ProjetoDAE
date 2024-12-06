package com.example.projetodae.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class User {

    @Id
    @NotNull
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private TipoUser tipouser;

    public User() {
    }

    public User(int id, String username, String password, TipoUser tipouser) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipouser = tipouser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUser getTipouser() {
        return tipouser;
    }

    public void setTipouser(TipoUser tipouser) {
        this.tipouser = tipouser;
    }
}
