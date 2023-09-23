import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class _07_SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Deque<String> textStack = new ArrayDeque<>();
        String text = "";

        for (int i = 0; i < n; i++) {
            String[] commandParts = scanner.nextLine().split(" ");

            switch (commandParts[0]) {
                case "1" :
                    String subString = commandParts[1];
                    textStack.push(text);
                    text += subString;
                    break;
                case "2" :
                    textStack.push(text);
                    int count = Integer.parseInt(commandParts[1]);
                    text = text.substring(0, text.length() - count);
                    break;
                case "3" :
                    int index = Integer.parseInt(commandParts[1]);
                    System.out.println(text.charAt(index - 1));
                    break;
                case "4" :
                    text = textStack.pop();
                    break;
            }
        }
    }
}
