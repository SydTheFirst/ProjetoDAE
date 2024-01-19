package packages.projetodae.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.EndCustomer;

@Stateless
public class EndCustomerBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, String name, String address, String email) {
        var endCustomer = new EndCustomer(username, password, name, email, address);
        entityManager.persist(endCustomer);
    }
}
