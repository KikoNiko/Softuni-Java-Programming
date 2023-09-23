import java.util.*;

public class _02_BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s");

        //number of elements to push to stack
        int n = Integer.parseInt(tokens[0]);
        //number of elements to pop out of stack
        int s = Integer.parseInt(tokens[1]);
        //check if element 'x' is in the stack
        int x = Integer.parseInt(tokens[2]);

        Deque<Integer> numberStack = new ArrayDeque<>();

        int[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < n; i++) {
            numberStack.push(numbers[i]);
        }

        for (int i = 1; i <= s; i++) {
            numberStack.pop();
        }

        if (numberStack.isEmpty()) {
            System.out.println(0);
        } else if (numberStack.contains(x)) {
            System.out.println(true);
        } else {
            //return the smallest element in stack
            System.out.println(Collections.min(numberStack));
        }

    }
}
