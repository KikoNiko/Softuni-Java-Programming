import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;

public class ChangeCasing {

    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        em.getTransaction().begin();

        List<Town> resultList = em.createQuery("FROM Town ", Town.class).getResultList();

        for (Town town : resultList) {
            String townName = town.getName();

            if(townName.length() <= 5) {
                town.setName(townName.toUpperCase());
                em.persist(town);
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
