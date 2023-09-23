import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class _05_BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String result = isBalanced(input) ? "YES" : "NO";
        System.out.println(result);

    }

    private static boolean isBalanced(String input) {
        Deque<Character> openBracesStack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            char brace = input.charAt(i);
            switch (brace) {
                case '}':
                    if (openBracesStack.isEmpty()) {
                        return false;
                    }
                    char openBrace = openBracesStack.pop();
                    if (openBrace != '{') return false;
                    break;
                case ']':
                    if (openBracesStack.isEmpty()) {
                        return false;
                    }
                    openBrace = openBracesStack.pop();
                    if (openBrace != '[') return false;
                    break;
                case ')':
                    if (openBracesStack.isEmpty()) {
                        return false;
                    }
                    openBrace = openBracesStack.pop();
                    if (openBrace != '(') return false;
                    break;
                default:
                    openBracesStack.push(brace);
            }
        }
        return true;
    }
}
