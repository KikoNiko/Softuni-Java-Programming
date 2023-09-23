import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Scanner;

public class _03_MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> stack = new ArrayDeque<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String command = scanner.nextLine();
            if (command.length() > 1) {
                int number = Integer.parseInt(command.split("\\s")[1]);
                stack.push(number);
            } else {
                if (command.equals("2")) {
                    stack.pop();
                } else if (command.equals("3")) {
                    System.out.println(Collections.max(stack));
                }
            }
        }
    }
}
