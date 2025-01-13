package com.example.projetodae.dtos;

import com.example.projetodae.entities.TipoUser;


public class UserDTO {
    private String username;
    private String password;
    private TipoUser tipouser;

    // Construtor padrão
    public UserDTO() {
    }

    // Construtor completo
    public UserDTO(String username, String password, TipoUser tipouser) {
        this.username = username;
        this.password = password;
        this.tipouser = tipouser;
    }

    // Getters e Setters

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
