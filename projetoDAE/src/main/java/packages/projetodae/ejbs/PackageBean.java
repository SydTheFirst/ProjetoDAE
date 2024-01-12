@Stateless
public class PackageBean {
    @PersistenceContext
    private EntityManager entityManager;
    public void create() {
        var package = new Package();
        entityManager.persist(package);
    }
}