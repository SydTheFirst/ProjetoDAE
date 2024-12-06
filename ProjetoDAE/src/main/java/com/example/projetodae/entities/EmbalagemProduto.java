package com.example.projetodae.entities;

public class EmbalagemProduto {
    private int idEmbalagem;
    private int idProduto;
    private int quantidade;

    // Construtor
    public EmbalagemProduto(int idEmbalagem, int idProduto, int quantidade) {
        this.idEmbalagem = idEmbalagem;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getIdEmbalagem() {
        return idEmbalagem;
    }

    public void setIdEmbalagem(int idEmbalagem) {
        this.idEmbalagem = idEmbalagem;
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