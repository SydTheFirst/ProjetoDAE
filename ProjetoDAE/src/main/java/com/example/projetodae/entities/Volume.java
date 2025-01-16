package com.example.projetodae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name = "volumes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllVolumes",
                query = "SELECT v FROM Volume v ORDER BY v.id" // JPQL
        ),
        @NamedQuery(
                name = "getVolumesByEncomenda",
                query = "SELECT v FROM Volume v WHERE v.idEncomenda = :idEncomenda" // JPQL
        )
})
public class Volume {

    @Id
    @NotNull
    private String id;

    @NotNull
    private int idEncomenda;

    public Volume() {
    }

    public Volume(String id, int idEncomenda) {
        this.id = id;
        this.idEncomenda = idEncomenda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }
}