package com.example.projetodae.ejbs;

import com.example.projetodae.entities.RegistoSensor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.sql.Timestamp;
import java.util.List;

@Stateless
public class RegistoSensorBean {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean exists(int id) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(r.id) FROM RegistoSensor r WHERE r.id = :id",
                Long.class
        );
        query.setParameter("id", id);
        return (Long) query.getSingleResult() > 0L;
    }

    public void create(int idSensor, Timestamp timeStamp, String valor) {
        var registoSensor = new RegistoSensor();
        registoSensor.setIdSensor(idSensor);
        registoSensor.setTimeStamp(timeStamp);
        registoSensor.setValor(valor);
        entityManager.persist(registoSensor);
    }

    public RegistoSensor find(int id) {
        return entityManager.find(RegistoSensor.class, id);
    }

    public List<RegistoSensor> getAllRegistos() {
        return entityManager.createNamedQuery("getAllRegistosSensor", RegistoSensor.class).getResultList();
    }

    public List<RegistoSensor> getRegistosByIdSensor(int idSensor) {
        Query query = entityManager.createNamedQuery("getRegistosSensorByIdSensor", RegistoSensor.class);
        query.setParameter("idSensor", idSensor);
        return query.getResultList();
    }

    public RegistoSensor getMostRecentRegistoSensor(int idSensor) {
        Query query = entityManager.createNamedQuery("getMostRecentRegistoSensor", RegistoSensor.class);
        query.setParameter("idSensor", idSensor);
        List<RegistoSensor> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public void updateRegisto(int id, int idSensor, Timestamp timeStamp, String valor) {
        RegistoSensor registoSensor = find(id);
        if (registoSensor != null) {
            registoSensor.setIdSensor(idSensor);
            registoSensor.setTimeStamp(timeStamp);
            registoSensor.setValor(valor);
            entityManager.merge(registoSensor);
        }
    }

    public boolean deleteRegisto(int id) {
        RegistoSensor registoSensor = find(id);
        if (registoSensor != null) {
            entityManager.remove(registoSensor);
            return true;
        }
        return false;
    }
}