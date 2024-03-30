import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {

    public static void main(String[] args) {
        String townName = new Scanner(System.in).nextLine();

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        Town townToDelete = entityManager.createQuery("FROM Town WHERE name = :tName", Town.class)
                .setParameter("tName", townName)
                .getSingleResult();

        List<Address> addressesToDelete = entityManager.createQuery("FROM Address WHERE town.name = :tName", Address.class)
                .setParameter("tName", townName)
                .getResultList();

        addressesToDelete.forEach(a -> a.getEmployees()
                .forEach(e -> e.setAddress(null)));

        addressesToDelete.forEach(entityManager::remove);
        entityManager.remove(townToDelete);

        int countDeleted = addressesToDelete.size();

        System.out.printf("%d address%s in %s deleted",
                countDeleted,
                countDeleted == 1 ? "" : "es",
                townName);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
