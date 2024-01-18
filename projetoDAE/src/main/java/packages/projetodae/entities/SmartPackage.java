package packages.projetodae.entities;

import jakarta.persistence.Id;

public class SmartPackage {
    @Id
    private int id;
    private int embalagemPaiId;
    private int encomendaId;
    private int nivel;
    private int produtoId;

    // Construtor vazio
    public SmartPackage() {
    }

    public SmartPackage(int id, int embalagemPaiId, int encomendaId, int nivel, int produtoId) {
        this.id = id;
        this.embalagemPaiId = embalagemPaiId;
        this.encomendaId = encomendaId;
        this.nivel = nivel;
        this.produtoId = produtoId;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmbalagemPaiId() {
        return embalagemPaiId;
    }

    public void setEmbalagemPaiId(int embalagemPaiId) {
        this.embalagemPaiId = embalagemPaiId;
    }

    public int getEncomendaId() {
        return encomendaId;
    }

    public void setEncomendaId(int encomendaId) {
        this.encomendaId = encomendaId;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }
}
