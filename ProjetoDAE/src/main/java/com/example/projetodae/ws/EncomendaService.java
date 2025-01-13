package com.example.projetodae.ws;

import com.example.projetodae.dtos.EncomendaDTO;
import com.example.projetodae.dtos.RegistoSensorDTO;
import com.example.projetodae.ejbs.EncomendaBean;
import com.example.projetodae.entities.Encomenda;
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
@Path("/encomendas")
@Produces("application/json")
@Consumes("application/json")
public class EncomendaService {

    @EJB
    private EncomendaBean encomendaBean;


    @GET
    public List<EncomendaDTO> getAllEncomendas(){
        return DTOconverter.encomendasToDTOs(encomendaBean.getAllEncomendas());
    }

    @GET
    @Path("/{id}")
    public Response getEncomenda(@PathParam("id") int id){
        Encomenda encomenda = encomendaBean.find(id);
        if (encomenda == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(DTOconverter.toDTO(encomenda)).build();
    }

    @PUT
    @Path("{encomendaId}")
    public Response updateEncomenda(@PathParam("encomendaId") int id, EncomendaDTO encomendaDTO){

        Encomenda encomenda = encomendaBean.find(id);
        if (encomenda == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        encomendaBean.updateEncomenda(
                id,
                encomendaDTO.getDataPartida(),
                encomendaDTO.getDataChegada(),
                encomendaDTO.getMetodoPagamento(),
                encomendaDTO.getStatus()
        );
        return Response.ok(DTOconverter.toDTO(encomenda)).build();
    }


    @DELETE
    @Path("{encomendaId}")
    public Response deleteEncomenda(@PathParam("encomendaId") int id){
        Encomenda encomenda = encomendaBean.find(id);
        if (encomenda == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        encomendaBean.deleteEncomenda(id);
        return Response.noContent().build();
    }

}
