package MoreExc;

import java.util.Arrays;
import java.util.Scanner;

public class TheLift {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalTourists = Integer.parseInt(scanner.nextLine());
        int[] liftWagons = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean isFull = true;
        for (int i = 0; i < liftWagons.length; i++) {
            if (totalTourists == 0) break;
            if (liftWagons[i] == 4) continue;

            int diff = 0;
            int touristsToRemove = 4;
            if (liftWagons[i] > 0) {
                diff = touristsToRemove - liftWagons[i];
                touristsToRemove = diff;
            }
            if (totalTourists < touristsToRemove) {
                liftWagons[i] += totalTourists;
                isFull = false;
                totalTourists = 0;
                break;
            } else {
                liftWagons[i] += touristsToRemove;
                totalTourists -= touristsToRemove;
            }
        }

        if (totalTourists != 0) {
            System.out.printf("There isn't enough space! %d people in a queue! %n", totalTourists);
        } else if (!isFull) {
            System.out.println("The lift has empty spots! ");
        }

        System.out.println(Arrays.toString(liftWagons).replaceAll("[\\[\\],]", ""));
    }
}
