package Exc;

import java.util.Arrays;
import java.util.Scanner;

public class ShootForTheWin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] targets = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();


        String input = scanner.nextLine();
        int targetsShot = 0;
        while (!input.equals("End")) {
            int targetIndex = Integer.parseInt(input);
            if (targetIndex >= 0 && targetIndex < targets.length) {
                if (targets[targetIndex] == -1) {
                    continue;
                }
                for (int i = 0; i < targets.length; i++) {
                    if (targets[i] == -1 || i == targetIndex) {
                        continue;
                    }
                    if (targets[i] > targets[targetIndex]) {
                        targets[i] -= targets[targetIndex];
                    } else {
                        targets[i] += targets[targetIndex];
                    }
                }
                targets[targetIndex] = -1;
                targetsShot++;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Shot targets: %d -> ", targetsShot);
        System.out.println(Arrays.toString(targets).replaceAll("[\\[\\],]", ""));
    }
}
