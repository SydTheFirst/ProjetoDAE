package com.example.projetodae.dtos;

import com.example.projetodae.entities.TipoSensor;

public class SensorDTO {

    private int id;
    private int idEmbalagem;
    private TipoSensor tipoSensor;
    private boolean ativo;

    // Construtor padrão
    public SensorDTO() {
    }

    // Construtor completo
    public SensorDTO(int id, int idEmbalagem, TipoSensor tipoSensor, boolean ativo) {
        this.id = id;
        this.idEmbalagem = idEmbalagem;
        this.tipoSensor = tipoSensor;
        this.ativo = ativo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmbalagem() {
        return idEmbalagem;
    }

    public void setIdEmbalagem(int idEmbalagem) {
        this.idEmbalagem = idEmbalagem;
    }

    public TipoSensor getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(TipoSensor tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
