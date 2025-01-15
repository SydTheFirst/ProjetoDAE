package com.example.projetodae.dtos;


public class EmbalagemDTO {

    private int id;
    private int idEncomenda;
    private String idVolume;
    private int idProduto;
    private int quantidade;

    // Construtor padrão
    public EmbalagemDTO() {
    }

    // Construtor
    public EmbalagemDTO(int id, String idVolume, int idEncomenda, int idProduto, int quantidade) {
        this.id = id;
        this.idVolume = idVolume;
        this.idEncomenda = idEncomenda;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }

    public String getIdVolume() {
        return idVolume;
    }

    public void setIdVolume(String idVolume) {
        this.idVolume = idVolume;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
