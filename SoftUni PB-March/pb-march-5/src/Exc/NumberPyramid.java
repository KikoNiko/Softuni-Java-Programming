package Exc;

import java.util.Scanner;

public class NumberPyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int count = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (count >= n+1) {
                    break;
                }
                System.out.print(count + " ");
                count++;
            }
            if (count >= n+1) {
                break;
            }
            System.out.println();
        }
    }
}
