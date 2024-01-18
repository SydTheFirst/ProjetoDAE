package packages.projetodae.entities;

import jakarta.persistence.Id;

public class Produto {
    @Id
    private int id;
    private String nome;
    private int fabricanteId;
    private int SensorId;

    // Construtor
    public Produto(int id, String nome, int fabricanteId, int infoDoProdutoId) {
        this.id = id;
        this.nome = nome;
        this.fabricanteId = fabricanteId;
        this.SensorId = infoDoProdutoId;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFabricanteId() {
        return fabricanteId;
    }

    public void setFabricanteId(int fabricanteId) {
        this.fabricanteId = fabricanteId;
    }

    public int getSensorId() {
        return SensorId;
    }

    public void setSensorId(int sensorId) {
        this.SensorId = sensorId;
    }
}
