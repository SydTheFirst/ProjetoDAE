package packages.projetodae.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import packages.projetodae.dtos.EndCustomerDTO;
import packages.projetodae.ejbs.EndCustomerBean;
import packages.projetodae.entities.EndCustomer;
import java.util.List;

import java.awt.*;
import java.util.stream.Collectors;

@Path("endCustomer")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class EndCustomerService {
    @EJB
    private EndCustomerBean endCustomerBean;

    private EndCustomerDTO toDTO(EndCustomer endCustomer) {
        return new EndCustomerDTO(
                endCustomer.getUsername(),
                endCustomer.getPassword(),
                endCustomer.getName(),
                endCustomer.getAddress(),
                endCustomer.getEmail()
        );
    }

    private List<EndCustomerDTO> toDTOs(List<EndCustomer> endCustomers) {
        return endCustomers.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<EndCustomerDTO> getAllEndCustomers() {
        return toDTOs(endCustomerBean.getAll());
    }
}
