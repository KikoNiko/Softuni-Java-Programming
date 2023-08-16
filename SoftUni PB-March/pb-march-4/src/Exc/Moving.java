package Exc;

import java.util.Scanner;

public class Moving {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());

        int area = width * length * height;
        int boxSum = 0;

        String input = scanner.nextLine();

        while (!input.equals("Done")) {
            int boxes = Integer.parseInt(input);
            boxSum += boxes;
            if (boxSum >= area) {
                System.out.printf("No more free space! You need %d Cubic meters more.", boxSum - area);
                break;
            }
            input = scanner.nextLine();
        }

        if (area > boxSum) {
            System.out.printf("%d Cubic meters left.", area - boxSum);
        }
    }
}
