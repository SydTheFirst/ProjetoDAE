package com.example.projetodae.dtos;


public class VolumeDTO {

    private int id;
    private int idEncomenda;

    public VolumeDTO() {
    }

    public VolumeDTO(int id, int idEncomenda) {
        this.id = id;
        this.idEncomenda = idEncomenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }
}
