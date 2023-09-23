import javax.print.attribute.standard.PresentationDirection;
import java.util.*;

public class _02_SimpleCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ArrayDeque<String> stack = new ArrayDeque<>();

        List<String> partsList = Arrays.asList(input.split(" "));
        stack.addAll(partsList);

        while (stack.size() > 1) {
            int first = Integer.parseInt(stack.pop());
            String op = stack.pop();
            int second = Integer.parseInt(stack.pop());

            int result = 0;
            if (op.equals("+")) {
                result = first + second;
            } else if (op.equals("-")) {
                result = first - second;
            }

            stack.push(result +  "");

        }
        System.out.println(stack.peek());
    }
}
