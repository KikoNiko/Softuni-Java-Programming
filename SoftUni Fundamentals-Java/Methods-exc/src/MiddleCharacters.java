import java.util.Scanner;

public class MiddleCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        findMiddleChar(input);
    }

    public static void findMiddleChar(String str) {
        int middleIndex = str.length() / 2;
        if (str.length() % 2 != 0) {
            System.out.println(str.charAt(middleIndex));
        } else {
            System.out.println(str.charAt(middleIndex - 1) + "" + str.charAt(middleIndex));
        }
    }
}
