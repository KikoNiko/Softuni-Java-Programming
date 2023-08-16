import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Students {

    public static class Student {
        private String firstName;
        private String lastName;
        private int age;
        private String hometown;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        public String getHometown() {
            return hometown;
        }

        public Student(String firstName, String lastName, int age, String hometown) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.hometown = hometown;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student> studentList = new ArrayList<>();

        String input = scanner.nextLine();
        while (!input.equals("end")) {
            String[] inputArr = input.split(" ");

            String firstName = inputArr[0];
            String lastName = inputArr[1];
            int age = Integer.parseInt(inputArr[2]);
            String hometown = inputArr[3];

            Student student = new Student(firstName, lastName, age, hometown);
            studentList.add(student);

            input = scanner.nextLine();
        }

        String town = scanner.nextLine();
        for (Student student : studentList) {
            if (student.getHometown().equals(town)) {
                System.out.printf("%s %s is %d years old\n", student.getFirstName(), student.getLastName(), student.getAge());
            }
        }
    }
}
