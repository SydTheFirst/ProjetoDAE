package com.example.projetodae.ejbs;

import com.example.projetodae.entities.Sensor;
import com.example.projetodae.entities.TipoSensor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Hibernate;

import java.util.List;

@Stateless
public class SensorBean {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean exists(int id) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(s.id) FROM Sensor s WHERE s.id = :id",
                Long.class
        );
        query.setParameter("id", id);
        return (Long) query.getSingleResult() > 0L;
    }

    public Sensor create(int idEmbalagem, TipoSensor tipoSensor, boolean ativo) {
        Sensor sensor = new Sensor(idEmbalagem, tipoSensor, ativo);
        entityManager.persist(sensor);
        return sensor;
    }

    public Sensor find(int id) {
        return entityManager.find(Sensor.class, id);
    }

    public List<Sensor> getAllSensors() {
        return entityManager.createNamedQuery("getAllSensors", Sensor.class).getResultList();
    }

    public List<Sensor> getSensorsByEmbalagem(int idEmbalagem) {
        Query query = entityManager.createNamedQuery("getSensorsByEmbalagem", Sensor.class);
        query.setParameter("idEmbalagem", idEmbalagem);
        return query.getResultList();
    }

    public List<Sensor> getNonActiveSensors() {
        return entityManager.createNamedQuery("getNonActiveSensors", Sensor.class).getResultList();
    }

    public void updateSensor(int id, int idEmbalagem, TipoSensor tipoSensor, boolean ativo) {
        Sensor sensor = find(id);
        if (sensor != null) {
            sensor.setIdEmbalagem(idEmbalagem);
            sensor.setTipoSensor(tipoSensor);
            sensor.setAtivo(ativo);
            entityManager.merge(sensor);
        }
    }

    public boolean deleteSensor(int id) {
        Sensor sensor = find(id);
        if (sensor != null) {
            entityManager.remove(sensor);
            return true;
        }
        return false;
    }
}