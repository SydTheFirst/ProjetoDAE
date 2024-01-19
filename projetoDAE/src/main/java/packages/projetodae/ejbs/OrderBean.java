package packages.projetodae.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.EndCustomer;
import packages.projetodae.entities.LogisticsOperator;
import packages.projetodae.entities.Order;
import packages.projetodae.entities.SmartPackage;

@Stateless
public class OrderBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(int id, EndCustomer endCustomer, LogisticsOperator logisticsOperator, String state, SmartPackage smartPackage) {
        var order = new Order(id, endCustomer, logisticsOperator, state, smartPackage);
        entityManager.persist(order);
    }
}
