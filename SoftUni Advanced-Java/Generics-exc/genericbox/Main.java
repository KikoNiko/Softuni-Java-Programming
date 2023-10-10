package genericbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Box<Double>> boxList = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            Box<Double> box = new Box<>(Double.parseDouble(scanner.nextLine()));
            boxList.add(box);
        }

        Box<Double> stringBox = new Box<>(Double.parseDouble(scanner.nextLine()));
        System.out.println(countGreaterElements(boxList, stringBox));

    }

    static <T extends Comparable<T>> int countGreaterElements(List<T> data, T element) {
        int count = 0;
        for (T e : data) {
            int res = e.compareTo(element);
            if (res > 0) {
                count++;
            }
        }
        return count;
    }

    static <T> void swap(List<T> data, int firstIndex, int secondIndex) {
        T firstElement = data.get(firstIndex);
        T secondElement = data.get(secondIndex);

        data.set(firstIndex, secondElement);
        data.set(secondIndex, firstElement);
    }
}
