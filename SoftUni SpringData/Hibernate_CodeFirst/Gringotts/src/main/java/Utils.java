import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Utils {

    private static final String DB_NAME = "gringotts";
    static EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory(DB_NAME)
                .createEntityManager();
    }
}
