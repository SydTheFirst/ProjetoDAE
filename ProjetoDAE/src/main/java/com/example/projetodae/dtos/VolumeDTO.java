package com.example.projetodae.dtos;


public class VolumeDTO {

    private String id;
    private int idEncomenda;

    public VolumeDTO() {
    }

    public VolumeDTO(String id, int idEncomenda) {
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
