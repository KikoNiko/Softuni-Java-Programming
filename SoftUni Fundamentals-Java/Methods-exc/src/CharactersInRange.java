import java.util.Scanner;

public class CharactersInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char first = scanner.nextLine().charAt(0);
        char second = scanner.nextLine().charAt(0);

        printCharsBetween(first, second);
    }

    public static void printCharsBetween(char first, char second) {
        int smaller = Math.min(first, second);
        int bigger = Math.max(first, second);
        for (int i = smaller + 1; i < bigger; i++) {
            System.out.print((char) i + " ");
        }
    }
}
