import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class ContainsEmployee {

    public static void main(String[] args) {
        EntityManager entityManager = Utils.createEntityManager();

        String fullName = new Scanner(System.in).nextLine();

        entityManager.getTransaction().begin();

        String isEmployeePresent = entityManager
                .createQuery("FROM Employee e WHERE CONCAT_WS(' ', e.firstName, e.lastName) = :fullName", Employee.class)
                .setParameter("fullName", fullName)
                .getResultList()
                .isEmpty() ? "No" : "Yes";

        entityManager.getTransaction().commit();

        System.out.println(isEmployeePresent);
    }
}
