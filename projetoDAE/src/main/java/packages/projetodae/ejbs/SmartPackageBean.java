package packages.projetodae.ejbs;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import packages.projetodae.entities.SmartPackage;

@Stateless
public class SmartPackageBean {
    @PersistenceContext
    private EntityManager entityManager;
    public void create() {
        var smartPackage = new SmartPackage();
        entityManager.persist(smartPackage);
    }
}