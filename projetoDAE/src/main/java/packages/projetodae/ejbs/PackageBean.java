package packages.projetodae.ejbs;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PackageBean {
    @PersistenceContext
    private EntityManager entityManager;
    public void create() {
        var package = new Package();
        entityManager.persist(package);
    }
}