package packages.projetodae.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.EndCustomer;
import packages.projetodae.entities.Order;
import packages.projetodae.entities.Product;

import java.util.List;

import java.awt.*;

@Stateless
public class EndCustomerBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, String name, String address, String email) {
        var endCustomer = new EndCustomer(username, password, name, email, address);
        entityManager.persist(endCustomer);
    }

    public List<EndCustomer> getAll() {
        return entityManager.createNamedQuery("getAllEndCustomers", EndCustomer.class).getResultList();
    }

    public void makeOrder(EndCustomer endCustomer, List<Product> products) {
        // Criar uma nova encomenda
        var order = new Order(endCustomer, "Pedido Feito", products);

        // Adicionar a encomenda à lista de encomendas do EndCustomer
        endCustomer.addEncomenda(order);

        // Persistir as mudanças no banco de dados
        entityManager.persist(endCustomer);
    }
}
