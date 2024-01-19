package packages.projetodae.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.Manufacturer;

@Stateless
public class ManufacturerBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, String name) {
        var manufacturer = new Manufacturer(username, password, name);
        entityManager.persist(manufacturer);
    }
}
