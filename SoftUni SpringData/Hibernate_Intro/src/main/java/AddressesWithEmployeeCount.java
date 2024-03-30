import entities.Address;

import javax.persistence.EntityManager;

public class AddressesWithEmployeeCount {

    public static void main(String[] args) {
        EntityManager entityManager = Utils.createEntityManager();

        entityManager.createQuery("FROM Address a ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);
    }
}
