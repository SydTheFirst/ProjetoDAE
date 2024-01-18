package packages.projetodae.entities;

import jakarta.persistence.Id;

import java.util.LinkedList;

public class ConsumidorFinal {
    @Id
    private int id;

    private String username;
    private String password;
    private String nome;
    private String morada;
    private String email;
    private LinkedList<Integer> encomendasId;

    // Construtor
    public ConsumidorFinal(int id, String username, String password, String nome, String morada, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.encomendasId = new LinkedList<>();
    }

    // Getters e Setters


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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedList<Integer> getEncomendasId() {
        return encomendasId;
    }

    public void addEncomenda(Integer encomendaId) {
        this.encomendasId.add(encomendaId);
    }
}
