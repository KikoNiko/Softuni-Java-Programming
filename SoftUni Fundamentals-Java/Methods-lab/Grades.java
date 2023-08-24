import java.util.Scanner;

public class Grades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double input = Double.parseDouble(scanner.nextLine());
        gradeInWords(input);
    }

    public static void gradeInWords(double num) {
        String grade = "";
        if (num <= 2.99) {
            grade = "Fail";
        } else if (num <= 3.49) {
            grade = "Poor";
        } else if (num <= 4.49) {
            grade = "Good";
        } else if (num <= 5.49) {
            grade = "Very good";
        } else {
            grade = "Excellent";
        }
        System.out.println(grade);
    }
}
