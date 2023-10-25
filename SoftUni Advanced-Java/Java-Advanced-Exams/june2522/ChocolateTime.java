package june2522;

import java.util.*;

public class ChocolateTime {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Double> milkQueue = new ArrayDeque<>();
        Deque<Double> cacaoStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble)
                .forEach(milkQueue::offer);
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble)
                .forEach(cacaoStack::push);

        Map<String, Integer> chocolateMap = new TreeMap<>();

        while (!milkQueue.isEmpty() && !cacaoStack.isEmpty()) {
            double cacao = cacaoStack.peek();
            double sum = milkQueue.peek() + cacao;
            double percentage = (cacao / sum) * 100;
            if (canMakeChocolate(percentage)) {
                addChocolate(chocolateMap, percentage);
                cacaoStack.pop();
                milkQueue.poll();
            } else {
                cacaoStack.pop();
                milkQueue.offer(milkQueue.pollFirst() + 10);
            }
        }

        if (chocolateMap.size() == 3) {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        } else {
            System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
        }

        for (Map.Entry<String, Integer> entry : chocolateMap.entrySet()) {
            System.out.printf("# %s --> %d%n", entry.getKey(), entry.getValue());
        }

    }

    private static void addChocolate(Map<String, Integer> map, double p) {
        StringBuilder choco = new StringBuilder();
        if (p == 30) {
            choco.append("Milk ");
        } else if (p == 50) {
            choco.append("Dark ");
        } else {
            choco.append("Baking ");
        }
        choco.append("Chocolate");
        String chocoString = choco.toString();
        if (map.containsKey(chocoString)) {
            map.put(chocoString, map.get(chocoString) + 1);
        } else {
            map.put(chocoString, 1);
        }
    }

    private static boolean canMakeChocolate(double p) {
        List<Double> values = List.of(30.0, 50.0, 100.0);
        return values.contains(p);
    }
}
