package packages.projetodae.entities;

import jakarta.persistence.Id;

import java.util.LinkedList;

public class Produto {
    @Id
    private int id;
    private String nome;
    private String fabricante;
    private LinkedList<Sensores> infoDoProduto;

    // Construtor
    public Produto(String nome, String fabricante, LinkedList<Sensores> infoDoProduto) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.infoDoProduto = infoDoProduto;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public LinkedList<Sensores> getInfoDoProduto() {
        return infoDoProduto;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setInfoDoProduto(LinkedList<Sensores> infoDoProduto) {
        this.infoDoProduto = infoDoProduto;
    }
}
