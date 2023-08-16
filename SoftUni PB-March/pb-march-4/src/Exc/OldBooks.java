package Exc;

import java.util.Scanner;

public class OldBooks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String favourite = scanner.nextLine();
        String book = scanner.nextLine();
        int count = 0;
        boolean isFound = false;

        while (!book.equals("No More Books")) {
            if (book.equals(favourite)) {
                isFound = true;
                break;
            }
            count++;
            book = scanner.nextLine();
        }
        if (isFound) {
            System.out.printf("You checked %d books and found it.", count);
        } else {
            System.out.println("The book you search is not here!");
            System.out.printf("You checked %d books.", count);
        }
    }
}
