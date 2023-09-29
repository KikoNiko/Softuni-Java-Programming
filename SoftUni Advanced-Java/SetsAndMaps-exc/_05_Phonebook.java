import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _05_Phonebook {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> phonebook = new HashMap<>();

        String input = scanner.nextLine();

        while (!"search".equals(input)) {

            String[] tokens = input.split("-");
            String name = tokens[0];
            String phone = tokens[1];
            phonebook.put(name, phone);

            input = scanner.nextLine();
        }

        input = scanner.nextLine();
        while (!"stop".equals(input)) {
            String name = input;
            if (!phonebook.containsKey(input)) {
                System.out.printf("Contact %s does not exist.%n", name);
            } else {
                System.out.printf("%s -> %s%n", name, phonebook.get(name));
            }
            input = scanner.nextLine();
        }
    }
}
