import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class _08_InfixToPostfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split(" ");

        Deque<String> outputQueue = new ArrayDeque<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (Character.isLetterOrDigit(token.charAt(0))) {
                outputQueue.offer(token);
            } else {
                switch (token) {
                    case "+" :
                    case "-" :
                        if (operatorStack.isEmpty()) {
                            operatorStack.push(token);
                            continue;
                        }
                        if (operatorStack.peek().equals("+") || operatorStack.peek().equals("-")) {
                            outputQueue.offer(operatorStack.pop());
                            operatorStack.push(token);
                        } else {
                            operatorStack.push(token);
                        }
                        break;
                    case "*" :
                    case "/" :
                        if (operatorStack.isEmpty()) {
                            operatorStack.push(token);
                            continue;
                        }
                        if (operatorStack.peek().equals("*") || operatorStack.peek().equals("/")) {
                            outputQueue.offer(operatorStack.pop());
                            operatorStack.push(token);
                        } else {
                            operatorStack.push(token);
                        }
                        break;
                    case "(" :
                        operatorStack.push(token);
                        break;
                    case ")" :
                        outputQueue.offer(operatorStack.pop());
                        operatorStack.push(token);
                        break;
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(") || operatorStack.peek().equals(")")) {
                operatorStack.pop();
            } else {
                outputQueue.offer(operatorStack.pop());
            }
        }
        for (String token : outputQueue) {
            System.out.print(token + " ");
        }
    }
}
