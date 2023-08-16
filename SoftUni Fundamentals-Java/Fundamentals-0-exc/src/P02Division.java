import java.util.Scanner;

public class P02Division {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String out = "";

        if (n % 10 == 0) {
            out = "The number is divisible by 10";
        } else if (n % 7 == 0) {
            out = "The number is divisible by 7";
        } else if (n % 6 == 0) {
            out = "The number is divisible by 6";
        } else if (n % 3 == 0) {
            out = "The number is divisible by 3";
        } else if (n % 2 == 0) {
            out = "The number is divisible by 2";
        } else {
            out = "Not divisible";
        }

        System.out.println(out);
    }
}
