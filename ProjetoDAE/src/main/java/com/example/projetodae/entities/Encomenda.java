package com.example.projetodae.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

public class Encomenda {

    @Id
    @NotNull
    private int id;

    @NotNull
    private int idCliente;

    @NotNull
    private Timestamp dataPartida;

    @NotNull
    private Timestamp dataChegada;

    @NotNull
    private MetodoPagamento metodoPagamento;

    @NotNull
    private Status status;

    public Encomenda() {
    }

    public Encomenda(int id, int idCliente, Timestamp dataPartida, Timestamp dataChegada, MetodoPagamento metodoPagamento, Status status) {
        this.id = id;
        this.idCliente = idCliente;
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
