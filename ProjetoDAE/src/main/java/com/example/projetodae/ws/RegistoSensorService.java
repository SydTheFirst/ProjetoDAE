package com.example.projetodae.ws;

import com.example.projetodae.dtos.RegistoSensorDTO;
import com.example.projetodae.ejbs.RegistoSensorBean;
import com.example.projetodae.entities.RegistoSensor;
import com.example.projetodae.security.Authenticated;
import com.example.projetodae.utils.DTOconverter;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/registos")
@Produces("application/json")
@Consumes("application/json")
@Authenticated
public class RegistoSensorService {
    @EJB
    private RegistoSensorBean registoSensorBean;

    @GET
    public List<RegistoSensorDTO> getAllRegistos() {
        return DTOconverter.registoSensorsToDTOs(registoSensorBean.getAllRegistos());
    }

    @GET
    @Path("/{id}")
    public Response getRegistoDetails(@PathParam("id") int id) {
        RegistoSensor registo = registoSensorBean.find(id);
        if (registo != null) {
            return Response.ok(DTOconverter.toDTO(registo)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/sensor/{idSensor}")
    public List<RegistoSensorDTO> getRegistosByIdSensor(@PathParam("idSensor") int idSensor) {
        return DTOconverter.registoSensorsToDTOs(registoSensorBean.getRegistosByIdSensor(idSensor));
    }

    @GET
    @Path("/sensor/{idSensor}/mostRecent")
    public Response getMostRecentRegistoSensor(@PathParam("idSensor") int idSensor) {
        RegistoSensor registo = registoSensorBean.getMostRecentRegistoSensor(idSensor);
        if (registo != null) {
            return Response.ok(DTOconverter.toDTO(registo)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
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

        return Response.ok(DTOconverter.toDTO(registo)).build();
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


}