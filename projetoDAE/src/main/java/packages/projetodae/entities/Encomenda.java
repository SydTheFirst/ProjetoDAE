package packages.projetodae.entities;

import java.util.LinkedList;

public class Encomenda {
    private ConsumidorFinal consumidorFinal;
    private String morada;
    private OperadorDeLogistica operadorDeLogistica;
    private LinkedList<Produto> produtos;

    // Construtor
    public Encomenda(ConsumidorFinal consumidorFinal, String morada, OperadorDeLogistica operadorDeLogistica, LinkedList<Produto> produtos) {
        this.consumidorFinal = consumidorFinal;
        this.morada = morada;
        this.operadorDeLogistica = operadorDeLogistica;
        this.produtos = produtos;
    }

    // Outros métodos
    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    //Getters e Setter

    // Getters
    public ConsumidorFinal getConsumidorFinal() {
        return consumidorFinal;
    }

    public String getMorada() {
        return morada;
    }

    public OperadorDeLogistica getOperadorDeLogistica() {
        return operadorDeLogistica;
    }

    public LinkedList<Produto> getProdutos() {
        return produtos;
    }

    // Setters
    public void setConsumidorFinal(ConsumidorFinal consumidorFinal) {
        this.consumidorFinal = consumidorFinal;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setOperadorDeLogistica(OperadorDeLogistica operadorDeLogistica) {
        this.operadorDeLogistica = operadorDeLogistica;
    }

    public void setProdutos(LinkedList<Produto> produtos) {
        this.produtos = produtos;
    }
}