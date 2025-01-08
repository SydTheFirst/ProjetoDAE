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
        return Response.ok(toDTO(encomenda)).build();
    }


    @POST
    @Path("/")
    public Response createEncomenda(EncomendaDTO encomendaDTO){

        encomendaBean.create(
                encomendaDTO.getIdCliente(),
                encomendaDTO.getDataPartida(),
                encomendaDTO.getDataChegada(),
                encomendaDTO.getMetodoPagamento(),
                encomendaDTO.getStatus()
        );

        //Adicionar forma de confirmar que criou com sucesso
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{encomendaId}")
    public Response updateEncomenda(@PathParam("encomendaId") int id, EncomendaDTO encomendaDTO){
        encomendaBean.updateEncomenda(
                id,
                encomendaDTO.getDataPartida(),
                encomendaDTO.getDataChegada(),
                encomendaDTO.getMetodoPagamento(),
                encomendaDTO.getStatus()
        );
        return Response.status(Response.Status.OK).build();
    }


    @DELETE
    @Path("{encomendaId}")
    public Response deleteEncomenda(@PathParam("encomendaId") int id){
        if (encomendaBean.deleteEncomenda(id)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT)
                .entity("CONFLICTING_DELETING")
                .build();
    }



    private EncomendaDTO toDTO(Encomenda encomenda) {
        return new EncomendaDTO(encomenda.getId(), encomenda.getIdCliente(), encomenda.getDataPartida(), encomenda.getDataChegada(), encomenda.getMetodoPagamento(), encomenda.getStatus());
    }

}
