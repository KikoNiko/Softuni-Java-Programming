import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {
    private static final String ADDRESS_TEXT = "Vitoshka 15";

    public static void main(String[] args) {
        EntityManager entityManager = Utils.createEntityManager();

        String lastName = new Scanner(System.in).nextLine();

        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText(ADDRESS_TEXT);
        entityManager.persist(newAddress);

        entityManager.createQuery("UPDATE Employee e SET e.address = :newAddress WHERE e.lastName = : ln")
                .setParameter("newAddress", newAddress)
                .setParameter("ln", lastName)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
