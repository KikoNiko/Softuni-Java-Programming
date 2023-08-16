import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        StringBuilder sb = new StringBuilder();
        char prevChar = 0;

        for (char c : input.toCharArray()) {
            if (c != prevChar) {
                sb.append(c);
                prevChar = c;
            }
        }

        System.out.println(sb);
    }
}
