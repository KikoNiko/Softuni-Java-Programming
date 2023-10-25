package regularExam21Oct23;

import java.util.*;
import java.util.stream.Collectors;

public class OffroadChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> fuelStack = new ArrayDeque<>();
        Deque<Integer> consumptionIndexQueue = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(fuelStack::push);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(consumptionIndexQueue::offer);

        List<Integer> amountFuelNeeded = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<String> altitudesReached = new ArrayList<>();
        int altitudesCounter = 1;
        boolean isReached = false;

        while (!amountFuelNeeded.isEmpty()) {
            String altitude = String.format("Altitude %d", altitudesCounter);
            int result = fuelStack.pop() - consumptionIndexQueue.pollFirst();
            int fuelNeeded = amountFuelNeeded.get(0);
            if (result >= fuelNeeded) {
                System.out.println("John has reached: " + altitude);
                amountFuelNeeded.remove(0);
                altitudesReached.add(altitude);
            } else {
                System.out.println("John did not reach: " + altitude);
                break;
            }
            altitudesCounter++;
        }
        if (amountFuelNeeded.isEmpty()) {
            isReached = true;
            System.out.println("John has reached all the altitudes and managed to reach the top!");
        }
        if (!isReached) {
            System.out.println("John failed to reach the top.");
            if (altitudesCounter > 1) {
                System.out.print("Reached altitudes: " + String.join(", ", altitudesReached));
            } else {
                System.out.println("John didn't reach any altitude.");
            }
        }
    }
}
