package oct2222;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnergyDrinks {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> caffeineStack = new ArrayDeque<>();
        Deque<Integer> energyDrinksQueue = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(caffeineStack::push);

        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(energyDrinksQueue::offer);

        int totalCaffeine = 0;

        while (!caffeineStack.isEmpty() && !energyDrinksQueue.isEmpty()) {
            int caffeineAmount = caffeineStack.pop() * energyDrinksQueue.peek();

            if (caffeineAmount + totalCaffeine <= 300) {
                totalCaffeine += caffeineAmount;
                energyDrinksQueue.poll();
            } else {
                energyDrinksQueue.offer(energyDrinksQueue.pollFirst());
                if (totalCaffeine >= 30) {
                    totalCaffeine -= 30;
                }
            }
        }

        if (!energyDrinksQueue.isEmpty()) {
            String output = energyDrinksQueue.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Drinks left: " + output);
        } else {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        }

        System.out.printf("Stamat is going to sleep with %d mg caffeine.", totalCaffeine);
    }
}
