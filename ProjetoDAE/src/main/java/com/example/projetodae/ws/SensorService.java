package com.example.projetodae.ws;

import com.example.projetodae.dtos.SensorDTO;
import com.example.projetodae.entities.Sensor;
import com.example.projetodae.entities.TipoSensor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/sensors")
@Produces("application/json")
@Consumes("application/json")
public class SensorService {

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<SensorDTO> getAllSensors() {
        List<Sensor> sensors = em.createNamedQuery("getAllSensors", Sensor.class).getResultList();
        return sensors.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getSensor(@PathParam("id") int id) {
        Sensor sensor = em.find(Sensor.class, id);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(toDTO(sensor)).build();
    }

    @POST
    public Response createSensor(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor(sensorDTO.getId(), sensorDTO.getIdEmbalagem(), sensorDTO.getTipoSensor(), sensorDTO.isAtivo());
        em.persist(sensor);
        return Response.status(Response.Status.CREATED).entity(toDTO(sensor)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSensor(@PathParam("id") int id, SensorDTO sensorDTO) {
        Sensor sensor = em.find(Sensor.class, id);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        sensor.setIdEmbalagem(sensorDTO.getIdEmbalagem());
        sensor.setTipoSensor(sensorDTO.getTipoSensor());
        sensor.setAtivo(sensorDTO.isAtivo());
        em.merge(sensor);
        return Response.ok(toDTO(sensor)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSensor(@PathParam("id") int id) {
        Sensor sensor = em.find(Sensor.class, id);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(sensor);
        return Response.noContent().build();
    }

    private SensorDTO toDTO(Sensor sensor) {
        return new SensorDTO(sensor.getId(), sensor.getIdEmbalagem(), sensor.getTipoSensor(), sensor.isAtivo());
    }
}
