package packages.projetodae.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.LogisticsOperator;
import packages.projetodae.entities.Warehouse;

@Stateless
public class LogisticsOperatorBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, String name, Warehouse warehouse) {
        var logisticsOperator = new LogisticsOperator(username, password, name, warehouse);
        entityManager.persist(logisticsOperator);
    }

}
