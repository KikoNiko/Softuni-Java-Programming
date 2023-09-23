import java.util.ArrayDeque;
import java.util.Scanner;

public class _01_BrowserHistory {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();

        String link = scanner.nextLine();

        while (!link.equals("Home")) {
            if (link.equals("back")) {
                if (stack.isEmpty() || stack.size() == 1) {
                    System.out.println("no previous URLs");

                    link = scanner.nextLine();
                    continue;

                } else {
                    stack.pop();
                }
            } else {
                stack.push(link);
            }

            System.out.println(stack.peek());

            link = scanner.nextLine();
        }
    }
}
