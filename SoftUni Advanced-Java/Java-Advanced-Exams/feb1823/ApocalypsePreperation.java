package feb1823;

import java.util.*;

public class ApocalypsePreperation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> textilesQueue = new ArrayDeque<>();
        Deque<Integer> medicamentsStack = new ArrayDeque<>();

        Map<String, Integer> healingItems = new TreeMap<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(textilesQueue::offer);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(medicamentsStack::push);

        while (!textilesQueue.isEmpty() && !medicamentsStack.isEmpty()) {
            int score = textilesQueue.peek() + medicamentsStack.peek();
            int diff = scoreDifference(score);
            if (diff == score) {
                createElement(healingItems, score);
                textilesQueue.poll();
                medicamentsStack.pop();
            } else if (diff == 10 && score < 100) {
                textilesQueue.poll();
                medicamentsStack.push(medicamentsStack. pop() + 10);
            } else {
                createElement(healingItems, score);
                textilesQueue.poll();
                medicamentsStack.pop();
                medicamentsStack.push(medicamentsStack.pop() + diff);
            }
        }

        boolean areEmpty = textilesQueue.isEmpty() && medicamentsStack.isEmpty();
        if (areEmpty) {
            System.out.println("Textiles and medicaments are both empty.");
        } else if (textilesQueue.isEmpty()) {
            System.out.println("Textiles are empty.");
        } else {
            System.out.println("Medicaments are empty.");
        }

        healingItems.entrySet().stream()
                .sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
                .forEach(e -> System.out.printf("%s - %d%n", e.getKey(), e.getValue()));

        if (!areEmpty) {
            if (medicamentsStack.isEmpty()) {
                String text = textilesQueue.toString();
                System.out.println("Textiles left: " + text.substring(1, text.length() - 1));
            } else if (textilesQueue.isEmpty()) {
                String meds = medicamentsStack.toString();
                System.out.println("Medicaments left: " + meds.substring(1, meds.length() - 1));
            }
        }

    }


    private static int scoreDifference(int score) {
        List<Integer> numbers = List.of(30, 40, 100);
        int diff = 0;
        if (numbers.contains(score)) {
            return score;
        } else {
            if (score > 100) {
                diff = score - 100;
            } else {
                diff = 10;
            }
        }
        return diff;
    }


    private static void createElement(Map<String, Integer> map, int score) {
        String element = "";
        if (score == 30) {
            element = "Patch";
        } else if (score == 40) {
            element = "Bandage";
        } else {
            element = "MedKit";
        }

        if (map.containsKey(element)) {
            map.put(element, map.get(element) + 1);
        } else {
            map.put(element, 1);
        }
    }
}
