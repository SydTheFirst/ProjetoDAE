package com.example.projetodae.ws;

import com.example.projetodae.dtos.SensorDTO;
import com.example.projetodae.ejbs.SensorBean;
import com.example.projetodae.entities.Sensor;
import com.example.projetodae.entities.TipoSensor;
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
@Path("/sensors")
@Produces("application/json")
@Consumes("application/json")
public class SensorService {

    @EJB
    private SensorBean sensorBean;

    @GET
    public List<SensorDTO> getAllSensors() {
        return DTOconverter.sensorsToDTOs(sensorBean.getAllSensors());
    }

    @GET
    @Path("/{id}")
    public Response getSensor(@PathParam("id") int id) {
        Sensor sensor = sensorBean.find(id);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(DTOconverter.toDTO(sensor)).build();
    }

    @GET
    @Path("/embalagem/{idEmbalagem}")
    public List<SensorDTO> getSensorsByEmbalagem(@PathParam("idEmbalagem") int idEmbalagem) {
        return DTOconverter.sensorsToDTOs(sensorBean.getSensorsByEmbalagem(idEmbalagem));
    }

    @GET
    @Path("/nonactive")
    public List<SensorDTO> getNonActiveSensors() {
        return DTOconverter.sensorsToDTOs(sensorBean.getNonActiveSensors());
    }

    @POST
    public Response createSensor(SensorDTO sensorDTO) {
        sensorBean.create(
                sensorDTO.getIdEmbalagem(),
                sensorDTO.getTipoSensor(),
                sensorDTO.isAtivo()
        );

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSensor(@PathParam("id") int id, SensorDTO sensorDTO) {
        Sensor sensor = sensorBean.find(id);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        sensorBean.updateSensor(
                id,
                sensorDTO.getIdEmbalagem(),
                sensorDTO.getTipoSensor(),
                sensorDTO.isAtivo()
        );

        return Response.ok(DTOconverter.toDTO(sensor)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSensor(@PathParam("id") int id) {
        Sensor sensor = sensorBean.find(id);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        sensorBean.deleteSensor(id);
        return Response.noContent().build();
    }

}
