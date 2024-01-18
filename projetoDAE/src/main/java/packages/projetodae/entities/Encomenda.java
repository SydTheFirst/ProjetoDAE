package packages.projetodae.entities;

import jakarta.persistence.Id;

import java.util.LinkedList;

public class Encomenda {
    @Id
    private int id;
    private int consumidorFinalId;
    private String morada;
    private int operadorDeLogisticaId;
    private LinkedList<Integer> embalagens;

    // Construtor


    public Encomenda(int id, int consumidorFinalId, String morada, int operadorDeLogisticaId) {
        this.id = id;
        this.consumidorFinalId = consumidorFinalId;
        this.morada = morada;
        this.operadorDeLogisticaId = operadorDeLogisticaId;
        this.embalagens = new LinkedList<>();
    }

    // Outros métodos
    public void addEmbalagem(Integer embalagemId) {
        this.embalagens.add(embalagemId);
    }

    //Getters e Setter

    // Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsumidorFinalId() {
        return consumidorFinalId;
    }

    public void setConsumidorFinalId(int consumidorFinalId) {
        this.consumidorFinalId = consumidorFinalId;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getOperadorDeLogisticaId() {
        return operadorDeLogisticaId;
    }

    public void setOperadorDeLogisticaId(int operadorDeLogisticaId) {
        this.operadorDeLogisticaId = operadorDeLogisticaId;
    }

    public LinkedList<Integer> getEmbalagens() {
        return embalagens;
    }
}