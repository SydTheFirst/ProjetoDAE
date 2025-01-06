package com.example.projetodae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Table(
        name = "embalagem",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllEmbalagens",
                query = "SELECT e FROM Embalagem e ORDER BY e.id" // JPQL
        ),
        @NamedQuery(
                name = "getEmbalagensByVolume",
                query = "SELECT e FROM Embalagem e WHERE e.idVolume = :idVolume ORDER BY e.idProduto" // JPQL
        ),
        @NamedQuery(
                name = "getEmbalagensByProduto",
                query = "SELECT e FROM Embalagem e WHERE e.idProduto = :idProduto ORDER BY e.quantidade DESC" // JPQL
        )
})

@Entity
public class Embalagem {

    @Id
    @NotNull
    private int id;

    @NotNull
    private int idVolume;

    @NotNull
    private int idProduto;

    @NotNull
    private int quantidade;

    // Construtor padrão
    public Embalagem() {
    }

    // Construtor
    public Embalagem(int id, int idEmbalagem, int idProduto, int quantidade) {
        this.id = id;
        this.idVolume = idEmbalagem;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVolume() {
        return idVolume;
    }

    public void setIdVolume(int idEmbalagem) {
        this.idVolume = idEmbalagem;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Embalagem that = (Embalagem) o;
        return idVolume == that.idVolume && idProduto == that.idProduto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVolume, idProduto);
    }
}
