import java.util.Scanner;

public class P01Ages {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());
        String output = "";

        if (input >= 0 && input <= 2) {
            output = "baby";
        } else if (input >= 3 && input <= 13) {
            output = "child";
        } else if (input >= 14 && input <= 19) {
            output = "teenager";
        } else if (input >= 20 && input <= 65) {
            output = "adult";
        }  else {
            output = "elder";
        }

        System.out.println(output);
    }
}
