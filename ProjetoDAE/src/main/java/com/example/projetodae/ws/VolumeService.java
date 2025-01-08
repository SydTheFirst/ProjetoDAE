package com.example.projetodae.ws;


import com.example.projetodae.dtos.VolumeDTO;
import com.example.projetodae.ejbs.VolumeBean;
import com.example.projetodae.entities.Volume;
import com.example.projetodae.utils.DTOconverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Stateless
@Path("/volumes")
@Produces("application/json")
@Consumes("application/json")
public class VolumeService {

    @EJB
    private VolumeBean volumeBean;

    @GET
    public List<VolumeDTO> getAllVolumes(){
        return DTOconverter.volumesToDTOs(volumeBean.getAllVolumes());
    }

    @GET
    @Path("/{id}")
    public Response getVolume(@PathParam("id") int id){
        Volume volume = volumeBean.find(id);
        if (volume == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(DTOconverter.toDTO(volume)).build();
    }

    @POST
    @Path("/")
    public Response createVolume(VolumeDTO volumeDTO){

        volumeBean.create(
                volumeDTO.getId(),
                volumeDTO.getIdEncomenda()
        );

        //Adicionar forma de confirmar que criou com sucesso
        return Response.status(Response.Status.CREATED).build();
    }


    @DELETE
    @Path("{volumeId}")
    public Response deleteVolume(@PathParam("volumeId") int id){
        Volume volume = volumeBean.find(id);
        if (volume == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        volumeBean.deleteVolume(id);
        return Response.noContent().build();
    }




}
