import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MergingLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> secondList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int minSize = Math.min(firstList.size(), secondList.size());

        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < minSize; i++) {
            resultList.add(firstList.get(i));
            resultList.add(secondList.get(i));
        }

        if (firstList.size() > secondList.size()) {
            List<Integer> remainingList = firstList.subList(minSize, firstList.size());
            resultList.addAll(remainingList);
        } else if (secondList.size() > firstList.size()) {
            List<Integer> remainingList = secondList.subList(minSize, secondList.size());
            resultList.addAll(remainingList);
        }

        System.out.println(resultList.toString()
                .replace("[", "")
                .replace(",", "")
                .replace("]", ""));
    }
}
