package packages.projetodae.entities;

import jakarta.persistence.Id;

public class OperadorDeLogistica {
    @Id
    private int id;

    private String username;
    private String password;
    private String nome;
    private int armazemId;

    // Construtor
    public OperadorDeLogistica(int id, String username, String password, String nome, int armazemId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.armazemId = armazemId;
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

    public int getArmazemId() {
        return armazemId;
    }

    public void setArmazemId(int armazemId) {
        this.armazemId = armazemId;
    }
}
