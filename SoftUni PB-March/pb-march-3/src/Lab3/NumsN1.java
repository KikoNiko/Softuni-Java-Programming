package Lab3;

import java.util.Scanner;

public class NumsN1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = n; i > 0; i--) {
            System.out.println(i);
        }
    }
}
