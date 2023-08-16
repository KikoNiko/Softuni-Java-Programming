import java.util.Scanner;

public class TextFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] bannedWords = scanner.nextLine().split(", ");
        String text = scanner.nextLine();

        for (String word : bannedWords) {
            String replacement = "";
            for (int i = 0; i < word.length(); i++) {
                replacement += "*";
            }
            text = text.replace(word, replacement);
        }

        System.out.println(text);
    }
}
