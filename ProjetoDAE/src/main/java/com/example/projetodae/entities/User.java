package com.example.projetodae.entities;


public class User {
    public enum TipoUser {
        Cliente,
        Logistica,
        Admin // Add more as needed
    }


    @Id
    @NotNull
    private int id;

    @NotNull
    private string username;

    @NotNull
    private string password;

    @NotNull
    private TipoUser tipouser;

    public User() {

    }

    public User(int id, string username, string password, com.example.projetodae.entities.User.TipoUser tipouser) {
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

    public string getUsername() {
        return username;
    }

    public void setUsername(string username) {
        this.username = username;
    }

    public string getPassword() {
        return password;
    }

    public void setPassword(string password) {
        this.password = password;
    }

    public com.example.projetodae.entities.User.TipoUser getTipouser() {
        return tipouser;
    }

    public void setTipouser(com.example.projetodae.entities.User.TipoUser tipouser) {
        this.tipouser = tipouser;
    }
}