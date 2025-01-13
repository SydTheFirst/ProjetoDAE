package com.example.projetodae.ws;

import com.example.projetodae.dtos.EmbalagemDTO;
import com.example.projetodae.ejbs.EmbalagemBean;
import com.example.projetodae.entities.Embalagem;
import com.example.projetodae.utils.DTOconverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Stateless
@Path("/embalagens")
@Produces("application/json")
@Consumes("application/json")
public class EmbalagemService {

    @EJB
    private EmbalagemBean embalagemBean;

    @GET
    public List<EmbalagemDTO> getAllEmbalagens(){
        return DTOconverter.embalagensDTOs(embalagemBean.getAllEmbalagens());
    }

    @GET
    @Path("/{id}")
    public Response getEmabalgem(@PathParam("id") int id){
        Embalagem embalagem = embalagemBean.find(id);
        if (embalagem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(DTOconverter.toDTO(embalagem)).build();
    }

    @POST
    @Path("/")
    public Response createEmbalagem(EmbalagemDTO embalagemDTO){

        embalagemBean.create(
                embalagemDTO.getId(),
                embalagemDTO.getIdVolume(),
                embalagemDTO.getIdProduto(),
                embalagemDTO.getQuantidade()
        );

        //Adicionar forma de confirmar que criou com sucesso
        return Response.status(Response.Status.CREATED).build();
    }


    @DELETE
    @Path("{volumeId}")
    public Response deleteEmabalgem(@PathParam("volumeId") int id){
        Embalagem embalagem = embalagemBean.find(id);
        if (embalagem == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        embalagemBean.deleteEmbalagem(id);
        return Response.noContent().build();
    }





}
