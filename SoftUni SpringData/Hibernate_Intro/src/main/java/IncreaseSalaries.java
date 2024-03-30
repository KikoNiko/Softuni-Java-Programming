import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    private static final List<String> DEPARTMENT_NAMES = List.of(
            "Engineering",
            "Tool Design",
            "Marketing",
            "Information Services");

    public static void main(String[] args) {
        EntityManager entityManager = Utils.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> filteredEmployees =
                entityManager.createQuery("FROM Employee WHERE department.name in (:dpNames)", Employee.class)
                .setParameter("dpNames", DEPARTMENT_NAMES)
                .getResultList();

                filteredEmployees.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();

        filteredEmployees
                .forEach(e -> System.out.printf("%s %s ($%.2f)%n",
                e.getFirstName(),
                e.getLastName(),
                e.getSalary()));
    }
}
