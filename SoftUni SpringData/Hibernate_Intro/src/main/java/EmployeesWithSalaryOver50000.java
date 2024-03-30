import entities.Employee;

import javax.persistence.EntityManager;

public class EmployeesWithSalaryOver50000 {

    public static void main(String[] args) {
        EntityManager entityManager = Utils.createEntityManager();

        entityManager
                .createQuery("FROM Employee e WHERE e.salary > 50000", Employee.class)
                .getResultList()
                .forEach(e -> System.out.println(e.getFirstName()));
    }
}
