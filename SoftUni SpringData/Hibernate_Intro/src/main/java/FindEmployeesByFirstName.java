import entities.Employee;

import java.util.Scanner;

public class FindEmployeesByFirstName {

    public static void main(String[] args) {
        Utils.createEntityManager()
                .createQuery("FROM Employee WHERE firstName LIKE CONCAT(:pattern, '%')", Employee.class)
                .setParameter("pattern", new Scanner(System.in).nextLine())
                .getResultList()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getJobTitle(),
                        e.getSalary()));
    }
}
