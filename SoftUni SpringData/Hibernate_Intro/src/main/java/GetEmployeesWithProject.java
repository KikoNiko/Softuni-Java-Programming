import entities.Employee;

import java.util.Scanner;

public class GetEmployeesWithProject {

    public static void main(String[] args) {
        Employee e = Utils.createEntityManager()
                .createQuery("FROM Employee WHERE id = :id", Employee.class)
                .setParameter("id", new Scanner(System.in).nextInt())
                .getSingleResult();

        System.out.println(e.printEmployeeWithProjects());
    }
}
