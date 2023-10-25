package april1322;

import java.util.*;
import java.util.stream.Collectors;

public class Blacksmith {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> steelQueue = new ArrayDeque<>();
        Deque<Integer> carbonStack = new ArrayDeque<>();

        Map<String, Integer> swordsMap = new TreeMap<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(steelQueue::offer);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(carbonStack::push);

        while (!steelQueue.isEmpty() || carbonStack.isEmpty()) {
            int sum = steelQueue.poll() + carbonStack.peek();

            if (canForgeSword(sum)) {
                forgeSword(swordsMap, sum);
                carbonStack.pop();
            } else {
                carbonStack.push(carbonStack.pop() + 5);
            }
        }

        int totalSwordsForged = 0;
        for (Map.Entry<String, Integer> entry : swordsMap.entrySet()) {
            totalSwordsForged += entry.getValue();
        }

        if (!swordsMap.isEmpty()) {
            System.out.printf("You have forged %d swords.%n", totalSwordsForged);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }

        System.out.print("Steel left: ");
        System.out.println(collectionToString(steelQueue));
        System.out.print("Carbon left: ");
        System.out.println(collectionToString(carbonStack));

        for (Map.Entry<String, Integer> entry : swordsMap.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }


    private static String collectionToString(Deque<Integer> deque) {
        if (deque.isEmpty()) {
            return "none";
        }
        return deque.stream().map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    private static void forgeSword(Map<String, Integer> map, int resources) {
        StringBuilder sword = new StringBuilder();
        if (resources == 70) {
            sword.append("Gladius");
        } else if (resources == 80) {
            sword.append("Shamshir");
        } else if (resources == 90) {
            sword.append("Katana");
        } else if (resources == 110) {
            sword.append("Sabre");
        }
        addSwordToMap(map, sword.toString());
    }

    private static void addSwordToMap(Map<String, Integer> map, String sword) {
        if (map.containsKey(sword)) {
            map.put(sword, map.get(sword) + 1);
        } else {
            map.put(sword, 1);
        }
    }

    private static boolean canForgeSword(int sum) {
        List<Integer> nums = List.of(70, 80, 90, 110);
        return nums.contains(sum);
    }
}
