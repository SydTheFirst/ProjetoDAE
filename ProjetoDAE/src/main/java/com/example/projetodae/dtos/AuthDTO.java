package com.example.projetodae.dtos;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;

public class AuthDTO implements Serializable{

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public AuthDTO() {
    }

    public AuthDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
}
