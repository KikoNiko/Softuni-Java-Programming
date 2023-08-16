import java.util.Scanner;

public class DigitsLettersAndOther {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        StringBuilder digits = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        StringBuilder others = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            } else if (Character.isLetter(c)) {
                letters.append(c);
            } else {
                others.append(c);
            }
        }

        System.out.println(digits);
        System.out.println(letters);
        System.out.println(others);

    }
}
