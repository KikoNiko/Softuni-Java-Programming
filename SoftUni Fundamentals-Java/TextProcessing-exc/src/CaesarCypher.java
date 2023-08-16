import java.util.Scanner;

public class CaesarCypher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        StringBuilder encryptedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            c += 3;
            encryptedText.append(c);
        }

        System.out.println(encryptedText);

    }
}
