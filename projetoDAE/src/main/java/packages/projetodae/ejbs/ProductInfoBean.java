package packages.projetodae.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.Product;
import packages.projetodae.entities.ProductInfo;

@Stateless
public class ProductInfoBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(int id, float temperature, int humidity, boolean OpenedPackage, String location, float pressure, Product product) {
        var productInfo = new ProductInfo(id, temperature, humidity, OpenedPackage, location, pressure, product);
        entityManager.persist(productInfo);
    }
}
