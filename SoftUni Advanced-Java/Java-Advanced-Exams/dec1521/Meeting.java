package dec1521;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Meeting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> malesStack = new ArrayDeque<>();
        Deque<Integer> femalesQueue = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(malesStack::push);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(femalesQueue::offer);

        int matches = 0;
        while (!malesStack.isEmpty() && !femalesQueue.isEmpty()) {
            var male = getPerson(malesStack);
            if (male == null) {
                break;
            }
            var female = getPerson(femalesQueue);
            if (female == null) {
                break;
            }

            if (male.equals(female)) {
                matches++;
                malesStack.pop();
                femalesQueue.pollFirst();
            } else {
                malesStack.push(malesStack.pop() - 2);
                femalesQueue.pollFirst();
            }
        }

        System.out.println(printOutput(matches, malesStack, femalesQueue));
    }

    public static String printOutput(int matches, Deque<Integer> males, Deque<Integer> females) {
        var stringOfMales = males.isEmpty() ? "none" :
                males.stream().map(Object::toString).collect(Collectors.joining(", "));;

        var stringOfFemales = females.isEmpty() ? "none" :
                females.stream().map(Object::toString).collect(Collectors.joining(", "));

        return String.format("Matches: %d%nMales left: %s%nFemales left: %s%n",
                matches, stringOfMales, stringOfFemales);
    }

    private static Integer getPerson(Deque<Integer> people) {

        while (!people.isEmpty()) {
            var person = people.peek();

            if (person <= 0) {
                people.pollFirst();
                continue;
            }

            if (person % 25 == 0) {
                people.pollFirst();
                people.pollFirst();
                continue;
            }
            return person;
        }
        return null;
    }

}
