package com.example.projetodae.dtos;


public class EmbalagemDTO {

    private int id;
    private int idVolume;
    private int idProduto;
    private int quantidade;

    // Construtor padrão
    public EmbalagemDTO() {
    }

    // Construtor
    public EmbalagemDTO(int id, int idEmbalagem, int idProduto, int quantidade) {
        this.id = id;
        this.idVolume = idEmbalagem;
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

    public int getIdVolume() {
        return idVolume;
    }

    public void setIdVolume(int idEmbalagem) {
        this.idVolume = idEmbalagem;
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
