package com.example.projetodae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllUsers",
                query = "SELECT u FROM User u ORDER BY u.id" // JPQL
        )
})
public class User {

    @Id
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @NotNull
    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private TipoUser tipouser;

    // Construtor padrão
    public User() {
    }

    // Construtor completo
    public User(String username, String password, TipoUser tipouser) {
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