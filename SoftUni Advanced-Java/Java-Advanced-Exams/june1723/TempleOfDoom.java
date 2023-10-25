package june1723;

import java.util.*;
import java.util.stream.Collectors;

public class TempleOfDoom {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Your task is to take the first tool from the tools
        //sequence and the last substance from the substances sequence.
        // Multiply the values and check the result.

        Deque<Integer> tools = new ArrayDeque<>(); //queue
        Deque<Integer> substances = new ArrayDeque<>(); //stack

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(tools::offer);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(substances::push);


        List<Integer> challenges = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        while (true) {

            if (isResolved(tools, substances, challenges)) {
                tools.poll();
                substances.pop();

                if (challenges.isEmpty()) {
                    System.out.println("Harry found an ostracon, which is dated to the 6th century BCE.");
                    break;
                }

            } else {
                tools.offer(tools.remove() + 1);
                int substance = substances.pop();
                if (substance > 1) {
                    substances.push(substance - 1);
                }

                if (tools.isEmpty() || substances.isEmpty()) {
                    System.out.println("Harry is lost in the temple. Oblivion awaits him.");
                    break;
                }
            }

        }

        if (!tools.isEmpty()) {
            System.out.println("Tools: " + tools.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
        }
        if (!substances.isEmpty()) {
            System.out.println("Substances: " + substances.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
        }
        if (!challenges.isEmpty()) {
            System.out.println("Challenges: " + challenges.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
        }

    }

    private static boolean isResolved(Deque<Integer> queue, Deque<Integer> stack, List<Integer> list) {
        if (queue.isEmpty() || stack.isEmpty()) {
            return false;
        }
        int tool = queue.peek();
        int substance = stack.peek();
        int result = tool * substance;

        if (list.contains(result)) {
            list.remove(Integer.valueOf(result));
            return true;
        }
        return false;
    }


}
