package com.example.projetodae.dtos;

import com.example.projetodae.entities.User;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

public class UserDTO {
    public enum TipoUser {
        Cliente,
        Logistica,
        Admin // Add more as needed
    }

    @NotNull
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private tipouser tipouser;


    public UserDTO(int id, String username, String password,tipouser tipouser) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipouser = "Cliente";
    }

    public static UserDTO from(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getTipouser(),
                Hibernate.getClass(user).getSimpleUsername()
        );
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

    public tipouser getTipouser() {
        return tipouser;
    }

    public void setTipouser(tipouser tipouser) {
        this.tipouser = tipouser;
    }
}
