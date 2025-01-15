package com.example.projetodae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@Table(
        name = "encomendas",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllEncomendas",
                query = "SELECT e FROM Encomenda e ORDER BY e.id ASC" // JPQL
        ),
        @NamedQuery(
                name = "getEncomendasByCliente",
                query = "SELECT e FROM Encomenda e WHERE e.cliente = :cliente ORDER BY e.id ASC" // JPQL
        )
})

@Entity
public class Encomenda {

    @Id
    private int id;

    private String cliente;

    @NotNull
    private Timestamp dataPartida;

    @NotNull
    private Timestamp dataChegada;

    @Enumerated(EnumType.STRING)
    @NotNull
    private MetodoPagamento metodoPagamento;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    public Encomenda() {
    }

    public Encomenda(int id, String cliente, Timestamp dataPartida, Timestamp dataChegada, MetodoPagamento metodoPagamento, Status status) {
        this.id = id;
        this.cliente = cliente;
        this.dataPartida = dataPartida;
        this.dataChegada = dataChegada;
        this.metodoPagamento = metodoPagamento;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Timestamp getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Timestamp dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Timestamp getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Timestamp dataChegada) {
        this.dataChegada = dataChegada;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}