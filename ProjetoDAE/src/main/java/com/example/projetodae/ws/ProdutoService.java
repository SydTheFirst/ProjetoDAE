package com.example.projetodae.ws;

import com.example.projetodae.dtos.ProdutoDTO;
import com.example.projetodae.ejbs.ProdutoBean;
import com.example.projetodae.entities.Produto;
import com.example.projetodae.utils.DTOconverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Stateless
@Path("/produtos")
@Produces("application/json")
@Consumes("application/json")
public class ProdutoService {

    @EJB
    private ProdutoBean produtoBean;


    @GET
    public List<ProdutoDTO> getAllProdutos(){
        return DTOconverter.produtosToDTOs(produtoBean.getAllProdutos());
    }

    @GET
    @Path("/{id}")
    public Response getProduto(@PathParam("id") int id){
        Produto produto = produtoBean.find(id);
        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(DTOconverter.toDTO(produto)).build();
    }

    @POST
    @Path("/")
    public Response createProduto(ProdutoDTO produtoDTO){
        produtoBean.create(
                produtoDTO.getId(),
                produtoDTO.getNome(),
                produtoDTO.getPreco(),
                produtoDTO.getCategoria()
        );
        return Response.status(Response.Status.CREATED).build();

    }

    @PUT
    @Path("{produtoId}")
    public Response updateProduto(@PathParam("produtoId") int id, ProdutoDTO produtoDTO){

        Produto produto = produtoBean.find(id);
        if (produto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        produtoBean.updateProduto(
                id,
                produtoDTO.getNome(),
                produtoDTO.getPreco(),
                produtoDTO.getCategoria()
        );
        return Response.ok(DTOconverter.toDTO(produto)).build();
    }


    @DELETE
    @Path("{produtoId}")
    public Response deleteProduto(@PathParam("produtoId") int id){
        Produto produto = produtoBean.find(id);
        if (produto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        produtoBean.deleteProduto(id);
        return Response.noContent().build();
    }

}
