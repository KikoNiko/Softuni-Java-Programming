package companyroster;

import java.util.List;

public class Department {

    private String name;

    private List<Employee> employeeList;
    private double averageSalary;

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, List<Employee> employeeList) {
        this.name = name;
        this.employeeList = employeeList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public double getAverageSalary() {
        double sum = 0;
        for (Employee employee : this.employeeList) {
            sum += employee.getSalary();
        }
        return sum;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

}
