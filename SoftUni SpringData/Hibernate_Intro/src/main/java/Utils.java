import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Utils {

    private static final String DB_NAME = "soft_uni";
    static EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory(DB_NAME)
                .createEntityManager();
    }
}
