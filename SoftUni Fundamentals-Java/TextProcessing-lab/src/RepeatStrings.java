import java.util.Scanner;

public class RepeatStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] textArr = scanner.nextLine().split(" ");
        StringBuilder resultText = new StringBuilder();

        for (String str : textArr) {
            for (int i = 0; i < str.length(); i++) {
                resultText.append(str);
            }
        }
        System.out.println(resultText);
    }
}
