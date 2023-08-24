import java.util.Scanner;

public class GreaterOfTwoValues {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = scanner.nextLine();
        switch (type) {
            case "int" :
                int firstNum = Integer.parseInt(scanner.nextLine());
                int secondNum = Integer.parseInt(scanner.nextLine());
                System.out.println(getMax(firstNum, secondNum));
                break;
            case "char" :
                char a = scanner.nextLine().charAt(0);
                char b = scanner.nextLine().charAt(0);
                System.out.println(getMax(a, b));
                break;
            case "string" :
                String first = scanner.nextLine();
                String second = scanner.nextLine();
                System.out.println(getMax(first, second));
                break;
        }
    }

    static int getMax(int a, int b) {
        return Math.max(a, b);
    }

    static char getMax(char a, char b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    static String getMax(String first, String second) {
        if (first.compareTo(second) >= 0) {
            return first;
        }
        return second;
    }
}
