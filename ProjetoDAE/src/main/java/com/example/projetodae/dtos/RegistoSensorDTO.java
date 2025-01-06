package com.example.projetodae.dtos;

import java.sql.Timestamp;

public class RegistoSensorDTO {

    private int id;
    private int idSensor;
    private Timestamp timeStamp;
    private String valor;

    // Construtor padrão
    public RegistoSensorDTO() {
    }

    // Construtor completo
    public RegistoSensorDTO(int idSensor, Timestamp timeStamp, String valor) {
        this.idSensor = idSensor;
        this.timeStamp = timeStamp;
        this.valor = valor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
