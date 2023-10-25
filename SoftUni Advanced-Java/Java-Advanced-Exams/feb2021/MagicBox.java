package feb2021;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class MagicBox {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> firstBoxQueue = new ArrayDeque<>();
        Deque<Integer> secondBoxStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(firstBoxQueue::offer);
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(secondBoxStack::push);

        int claimedItemsSum = 0;
        while (!firstBoxQueue.isEmpty() && !secondBoxStack.isEmpty()) {
            int sum = firstBoxQueue.peek() + secondBoxStack.peek();
            if (sum % 2 == 0) {
                claimedItemsSum += sum;
                firstBoxQueue.poll();
                secondBoxStack.pop();
            } else {
                firstBoxQueue.offer(secondBoxStack.pop());
            }
        }
        if (firstBoxQueue.isEmpty()) {
            System.out.println("First magic box is empty.");
        } else {
            System.out.println("Second magic box is empty.");
        }

        if (claimedItemsSum >= 90) {
            System.out.println("Wow, your prey was epic! Value: " + claimedItemsSum);
        } else {
            System.out.println("Poor prey... Value: " + claimedItemsSum);
        }
    }
}
