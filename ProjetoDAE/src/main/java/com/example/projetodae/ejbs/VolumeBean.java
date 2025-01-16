package com.example.projetodae.ejbs;

import com.example.projetodae.entities.Volume;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class VolumeBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(String id, int idEncomenda) {
        Volume volume = new Volume();
        volume.setId(id);
        volume.setIdEncomenda(idEncomenda);
        entityManager.persist(volume);
    }

    public Volume find(int id) {
        return entityManager.find(Volume.class, id);
    }

    public List<Volume> getAllVolumes() {
        return entityManager.createNamedQuery("getAllVolumes", Volume.class).getResultList();
    }

    public List<Volume> getVolumesByEncomenda(int idEncomenda) {
        TypedQuery<Volume> query = entityManager.createNamedQuery("getVolumesByEncomenda", Volume.class);
        query.setParameter("idEncomenda", idEncomenda);
        return query.getResultList();
    }

    public void updateVolume(int id, int idEncomenda) {
        Volume volume = find(id);
        if (volume != null) {
            volume.setIdEncomenda(idEncomenda);
            entityManager.merge(volume);
        }
    }

    public void deleteVolume(int id) {
        Volume volume = find(id);
        if (volume != null) {
            entityManager.remove(volume);
        }
    }
}