import java.util.Arrays;
import java.util.Scanner;

public class CommonElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstArr = scanner.nextLine().split(" ");
        String[] secondArr = scanner.nextLine().split(" ");

        for (String el2 : secondArr) {
            for (String el1 : firstArr) {
                if (el2.equals(el1)) {
                    System.out.print(el2 + " ");
                }
            }
        }
    }
}
