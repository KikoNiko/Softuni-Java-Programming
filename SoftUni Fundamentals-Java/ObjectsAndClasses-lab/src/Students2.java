import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Students2 {

    public static class Student2 {
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

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        public Student2 (String firstName, String lastName, int age, String hometown) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.hometown = hometown;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student2> studentList = new ArrayList<>();

        String input = scanner.nextLine();
        while (!input.equals("end")) {
            String[] inputArr = input.split(" ");

            String firstName = inputArr[0];
            String lastName = inputArr[1];
            int age = Integer.parseInt(inputArr[2]);
            String hometown = inputArr[3];


            Student2 student = studentList
                    .stream()
                    .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
                    .findFirst()
                    .orElse(null);

            if (student == null) {
                Student2 studentToAdd = new Student2(firstName, lastName, age, hometown);
                studentList.add(studentToAdd);
            } else {
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setAge(age);
                student.setHometown(hometown);
            }

            input = scanner.nextLine();
        }

        String filterTown = scanner.nextLine();
        for (Student2 student : studentList) {
            if (student.getHometown().equals(filterTown)) {
                System.out.printf("%s %s is %d years old\n", student.getFirstName(), student.getLastName(), student.getAge());
            }
        }
    }
}
