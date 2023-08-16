package Exc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HeartDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] neighborhood = Arrays.stream(scanner.nextLine()
                .split("@"))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> housesHadValentine = new ArrayList<>();

        String input = scanner.nextLine();
        int currentIndex = 0;
        while (!input.equals("Love!")) {
            int jumpIndex = Integer.parseInt(input.split(" ")[1]);
            currentIndex += jumpIndex;
            if (currentIndex > neighborhood.length - 1) {
                currentIndex = 0;
            }
            if (!housesHadValentine.isEmpty() && housesHadValentine.contains(currentIndex)) {
                System.out.printf("Place %d already had Valentine's day.%n", currentIndex);
            }

            neighborhood[currentIndex] -= 2;
            if (neighborhood[currentIndex] == 0) {
                System.out.printf("Place %d has Valentine's day.%n", currentIndex);
                housesHadValentine.add(currentIndex);
            }

            input = scanner.nextLine();
        }
        System.out.printf("Cupid's last position was %d.%n", currentIndex);
        if (housesHadValentine.size() == neighborhood.length) {
            System.out.println("Mission was successful.");
        } else {
            int failedPlaces = neighborhood.length - housesHadValentine.size();
            System.out.printf("Cupid has failed %d places.", failedPlaces);
        }
    }
}
