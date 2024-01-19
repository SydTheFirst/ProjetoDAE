package packages.projetodae.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.Product;
import packages.projetodae.entities.ProductInfo;

@Stateless
public class ProductBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(int id, String name, ProductInfo productInfo) {
        var product = new Product(id, name, productInfo);
        entityManager.persist(product);
    }
}
