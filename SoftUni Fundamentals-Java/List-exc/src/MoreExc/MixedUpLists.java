package MoreExc;

import java.util.*;
import java.util.stream.Collectors;

public class MixedUpLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstList = Arrays
                .stream(scanner.nextLine()
                .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> secondList = Arrays
                .stream(scanner.nextLine()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> resultList = mixTwoLists(firstList, secondList);

        int first = 0;
        int second = 0;
        if (firstList.size() > secondList.size()) {
            first = firstList.get(firstList.size() - 2);
            second = firstList.get(firstList.size() - 1);
        } else {
            first = secondList.get(0);
            second = secondList.get(1);
        }
        int start = Math.min(first, second);
        int end = Math.max(first, second);

        List<Integer> outputList = new ArrayList<>();
        for (int el : resultList) {
            if (el > start && el < end) {
                outputList.add(el);
            }
        }
        Collections.sort(outputList);
        System.out.print(outputList.toString().replaceAll("[\\[\\],]", ""));

    }

    public static List<Integer> mixTwoLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int limit = list2.size();
        if (list1.size() < list2.size()) {
            limit = list1.size();
        }
        int index = list2.size() - 1;
        for (int i = 0; i < limit; i++) {
            result.add(list1.get(i));
            result.add(list2.get(index));
            index--;
        }
        return result;
    }
}
