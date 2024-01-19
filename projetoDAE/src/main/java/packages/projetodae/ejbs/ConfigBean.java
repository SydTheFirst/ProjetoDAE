package packages.projetodae.ejbs;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
@Startup
@Singleton
public class ConfigBean {
    @EJB
    private SmartPackageBean smartPackageBean;
    @PostConstruct
    public void populateDB() {
        // . . .

    }
}
