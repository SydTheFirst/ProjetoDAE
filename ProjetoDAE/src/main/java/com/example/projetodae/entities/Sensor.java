package com.example.projetodae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name = "sensors",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllSensors",
                query = "SELECT s FROM Sensor s ORDER BY s.id" // JPQL
        )
})
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(nullable = false)
    private int idEmbalagem;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private TipoSensor tipoSensor;

    @NotNull
    @Column(nullable = false)
    private boolean ativo;

    // Construtor padrão
    public Sensor() {
    }

    // Construtor completo
    public Sensor(int id, int idEmbalagem, TipoSensor tipoSensor, boolean ativo) {
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