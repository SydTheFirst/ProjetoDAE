package com.example.projetodae.entities;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(
        name = "registos_sensor",
        uniqueConstraints = @UniqueConstraint(columnNames = {"idSensor", "timeStamp"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllRegistosSensor",
                query = "SELECT r FROM RegistoSensor r ORDER BY r.timeStamp ASC" // JPQL
        )
})
public class RegistoSensor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(nullable = false)
    private int idSensor;

    @NotNull
    @Column(nullable = false)
    private Timestamp timeStamp;

    @NotNull
    @Column(nullable = false, length = 255)
    private String valor;

    // Construtor padrão
    public RegistoSensor() {
    }

    // Construtor completo
    public RegistoSensor(int idSensor, Timestamp timeStamp, String valor) {
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