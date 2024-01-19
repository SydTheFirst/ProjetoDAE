package packages.projetodae.ejbs;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.Order;
import packages.projetodae.entities.SmartPackage;

@Stateless
public class SmartPackageBean {
    @PersistenceContext
    private EntityManager entityManager;
    public void create(int id, int parentPackageId, Order order, int level, int produtoId) {
        var smartPackage = new SmartPackage(id, parentPackageId, order, level, produtoId);
        entityManager.persist(smartPackage);
    }
}