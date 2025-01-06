package com.example.projetodae.ws;

import com.example.projetodae.dtos.RegistoSensorDTO;
import com.example.projetodae.entities.RegistoSensor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/registos")
@Produces("application/json")
@Consumes("application/json")
public class RegistoSensorService {

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<RegistoSensorDTO> getAllRegistos() {
        List<RegistoSensor> registos = em.createNamedQuery("getAllRegistosSensor", RegistoSensor.class).getResultList();
        return registos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getRegisto(@PathParam("id") int id) {
        RegistoSensor registo = em.find(RegistoSensor.class, id);
        if (registo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(toDTO(registo)).build();
    }

    @POST
    public Response createRegisto(RegistoSensorDTO registoDTO) {
        RegistoSensor registo = new RegistoSensor(registoDTO.getIdSensor(), registoDTO.getTimeStamp(), registoDTO.getValor());
        em.persist(registo);
        return Response.status(Response.Status.CREATED).entity(toDTO(registo)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRegisto(@PathParam("id") int id, RegistoSensorDTO registoDTO) {
        RegistoSensor registo = em.find(RegistoSensor.class, id);
        if (registo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        registo.setIdSensor(registoDTO.getIdSensor());
        registo.setTimeStamp(registoDTO.getTimeStamp());
        registo.setValor(registoDTO.getValor());
        em.merge(registo);
        return Response.ok(toDTO(registo)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRegisto(@PathParam("id") int id) {
        RegistoSensor registo = em.find(RegistoSensor.class, id);
        if (registo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(registo);
        return Response.noContent().build();
    }

    private RegistoSensorDTO toDTO(RegistoSensor registo) {
        return new RegistoSensorDTO(registo.getIdSensor(), registo.getTimeStamp(), registo.getValor());
    }
}