package packages.projetodae.entities;

import jakarta.persistence.Id;

import java.util.LinkedList;

public class Armazem {
    @Id
    private int id;
    private String nome;
    private String localizacao;
    private LinkedList<Integer> encomendas;
    private LinkedList<Integer> fabricantes;


    // Construtor
    public Armazem(int id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.encomendas = new LinkedList<>();
        this.fabricantes = new LinkedList<>();
    }

    // Outros métodos
    public void addEncomenda(Integer encomendaId) {
        this.encomendas.add(encomendaId);
    }

    public void addFabricante(Integer fabricanteId) {
        this.fabricantes.add(fabricanteId);
    }
}
