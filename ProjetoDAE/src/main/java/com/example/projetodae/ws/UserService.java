package com.example.projetodae.ws;


import com.example.projetodae.dtos.UserDTO;
import com.example.projetodae.ejbs.UserBean;
import com.example.projetodae.entities.User;

import com.example.projetodae.utils.DTOconverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Stateless
@Path("/users")
@Produces("application/json")
@Consumes("application/json")
public class UserService {

    @EJB
    private UserBean userBean;

    @GET
    public List<UserDTO> getAllUsers(){
        return DTOconverter.usersToDTOs(userBean.getAllUsers());
    }

    @GET
    @Path("/{username}")
    public Response getUser(@PathParam("username") String username){
        User user = userBean.find(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(DTOconverter.toDTO(user)).build();
    }

    @POST
    @Path("/")
    public Response createUser(UserDTO userDTO){

        userBean.create(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getTipouser()
        );

        //Adicionar forma de confirmar que criou com sucesso
        return Response.status(Response.Status.CREATED).build();
    }


    @DELETE
    @Path("{username}")
    public Response deleteUser(@PathParam("username") String username){
        User user = userBean.find(username);
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        userBean.deleteUser(username);
        return Response.noContent().build();
    }
    
}
