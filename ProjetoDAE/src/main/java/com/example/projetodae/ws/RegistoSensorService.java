package com.example.projetodae.ws;

import com.example.projetodae.dtos.RegistoSensorDTO;
import com.example.projetodae.ejbs.RegistoSensorBean;
import com.example.projetodae.entities.RegistoSensor;
import com.example.projetodae.utils.DTOconverter;
import jakarta.ejb.EJB;
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

    @EJB
    private RegistoSensorBean registoSensorBean;

    @GET
    public List<RegistoSensorDTO> getAllRegistos() {
        return DTOconverter.registoSensorsToDTOs(registoSensorBean.getAllRegistos());
    }

    @GET
    @Path("/{id}")
    public Response getRegisto(@PathParam("id") int id) {
        RegistoSensor registo = registoSensorBean.find(id);
        if (registo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(toDTO(registo)).build();
    }

    @POST
    public Response createRegisto(RegistoSensorDTO registoDTO) {
        registoSensorBean.create(
                registoDTO.getIdSensor(),
                registoDTO.getTimeStamp(),
                registoDTO.getValor()
        );

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRegisto(@PathParam("id") int id, RegistoSensorDTO registoDTO) {

        RegistoSensor registo = registoSensorBean.find(id);
        if (registo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        registoSensorBean.updateRegisto(
                id,
                registoDTO.getIdSensor(),
                registoDTO.getTimeStamp(),
                registoDTO.getValor()
        );

        return Response.ok(toDTO(registo)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRegisto(@PathParam("id") int id) {
        RegistoSensor registo = registoSensorBean.find(id);
        if (registo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        registoSensorBean.deleteRegisto(id);
        return Response.noContent().build();
    }

    private RegistoSensorDTO toDTO(RegistoSensor registo) {
        return new RegistoSensorDTO(registo.getIdSensor(), registo.getTimeStamp(), registo.getValor());
    }
}