package feb1922;

import java.util.*;

public class FoodFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Character> vowelsQueue = new ArrayDeque<>();
        Deque<Character> consonantsStack = new ArrayDeque<>();
        Map<String, StringBuilder> wordsMap = new LinkedHashMap<>();
        wordsMap.put("pear", new StringBuilder());
        wordsMap.put("flour", new StringBuilder());
        wordsMap.put("pork", new StringBuilder());
        wordsMap.put("olive", new StringBuilder());

        Arrays.stream(scanner.nextLine().split(" "))
                .map(s -> s.charAt(0))
                .forEach(vowelsQueue::offer);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(s -> s.charAt(0))
                .forEach(consonantsStack::push);


        while (!consonantsStack.isEmpty()) {
            char v = vowelsQueue.pop();
            putIfPresent(wordsMap, v);

            char c = consonantsStack.pop();
            putIfPresent(wordsMap, c);

            vowelsQueue.offer(v);
        }

        List<String> wordsFound = new ArrayList<>();
        int wordsCount = 0;
        for (Map.Entry<String, StringBuilder> entry : wordsMap.entrySet()) {
            if (entry.getKey().length() == entry.getValue().length()) {
                wordsCount++;
                wordsFound.add(entry.getKey());
            }
        }
        System.out.println("Words found: " + wordsCount);
        wordsFound.forEach(System.out::println);

    }

    private static void putIfPresent(Map<String, StringBuilder> map, char c) {
        for (Map.Entry<String, StringBuilder> entry : map.entrySet()) {
            for (char ch : entry.getKey().toCharArray()) {
                if (ch == c && !entry.getValue().toString().contains(String.valueOf(ch))) {
                    map.put(entry.getKey(), entry.getValue().append(c));
                }
            }
        }
    }

}
