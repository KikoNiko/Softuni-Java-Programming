import java.util.Scanner;

public class DayOfWeek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] daysOfWeek = {"Invalid day!", "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"};

        int input = Integer.parseInt(scanner.nextLine());
        if (input >= 0 && input <= 7) {
            System.out.println(daysOfWeek[input]);
        } else {
            System.out.println("Invalid day!");
        }

    }
}
