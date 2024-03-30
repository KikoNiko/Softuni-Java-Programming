import entities.Employee;

import javax.persistence.EntityManager;

public class EmployeesFromDepartment {
    private static final String PRINT_EMPLOYEE_FORMAT = "%s %s from %s - $%.2f%n";

    public static void main(String[] args) {
        EntityManager entityManager = Utils.createEntityManager();

        String department = "Research and Development";

        entityManager
                .createQuery("FROM Employee e WHERE department.name = :depName " +
                        "ORDER BY e.salary, e.id", Employee.class)
                .setParameter("depName", department)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_EMPLOYEE_FORMAT,
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary()));
    }
}
