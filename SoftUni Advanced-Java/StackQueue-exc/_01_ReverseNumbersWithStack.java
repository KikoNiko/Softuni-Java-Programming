
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class _01_ReverseNumbersWithStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> numberStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(numberStack::push);

        while (!numberStack.isEmpty()) {
            System.out.print(numberStack.pop() + " ");
        }

    }
}
