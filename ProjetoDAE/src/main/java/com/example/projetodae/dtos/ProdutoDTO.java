package com.example.projetodae.dtos;

import com.example.projetodae.entities.Categoria;

public class ProdutoDTO {

    private int id;
    private String nome;
    private float preco;
    private Categoria categoria;

    // Construtor padrão
    public ProdutoDTO() {
    }

    // Construtor completo
    public ProdutoDTO(int id, String nome, float preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
