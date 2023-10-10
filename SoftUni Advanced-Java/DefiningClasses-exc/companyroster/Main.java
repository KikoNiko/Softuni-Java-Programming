package companyroster;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Department> departmentsMap = new HashMap<>();

        while (n-- > 0) {
            String[] data = scanner.nextLine().split("\\s+");
            Employee employee;
            String employeeName = data[0];
            double salary = Double.parseDouble(data[1]);
            String position = data[2];
            String departmentName = data[3];

            if (data.length == 4) {
                employee = new Employee(employeeName, salary, position, departmentName);
            } else if (data.length == 5) {
                String email = data[4];
                employee = new Employee(employeeName, salary, position, departmentName, email);
            } else {
                String email = data[4];
                int age = Integer.parseInt(data[5]);
                employee = new Employee(employeeName, salary, position, departmentName, email, age);
            }

            if (departmentsMap.containsKey(departmentName)) {
                departmentsMap.get(departmentName).getEmployeeList().add(employee);
            } else {
                Department department = new Department(departmentName, new ArrayList<>());
                department.getEmployeeList().add(employee);
                departmentsMap.put(departmentName, department);
            }

        }

        double maxAvg = 0;
        String highestDep = "";
        for (Map.Entry<String, Department> entry : departmentsMap.entrySet()) {
            double currentAvg = entry.getValue().getAverageSalary();
            if (currentAvg > maxAvg) {
                maxAvg = currentAvg;
                highestDep = entry.getKey();
            }
        }

        System.out.println("Highest Average Salary: " + highestDep);
        departmentsMap.get(highestDep).getEmployeeList()
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .forEach(System.out::println);

    }

}
