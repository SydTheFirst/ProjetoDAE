package packages.projetodae.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.Warehouse;

@Stateless
public class WarehouseBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create() {
        var warehouse = new Warehouse();
        entityManager.persist(warehouse);
    }
}
