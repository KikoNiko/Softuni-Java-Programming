import java.util.*;

public class _04_BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s");

        //number of elements to push to stack
        int n = Integer.parseInt(tokens[0]);
        //number of elements to pop out of stack
        int s = Integer.parseInt(tokens[1]);
        //check if element 'x' is in the stack
        int x = Integer.parseInt(tokens[2]);

        Deque<Integer> numbersQueue = new ArrayDeque<>();

        int[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < n; i++) {
            numbersQueue.offer(numbers[i]);
        }

        for (int i = 1; i <= s; i++) {
            numbersQueue.poll();
        }

        if (numbersQueue.isEmpty()) {
            System.out.println(0);
        } else if (numbersQueue.contains(x)) {
            System.out.println(true);
        } else {
            //return the smallest element in stack
            System.out.println(Collections.min(numbersQueue));
        }
    }
}
