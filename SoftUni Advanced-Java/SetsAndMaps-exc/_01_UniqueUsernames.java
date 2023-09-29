import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class _01_UniqueUsernames {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> usernames = new LinkedHashSet<>();

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String text = scanner.nextLine();
            usernames.add(text);
        }

        usernames.forEach(System.out::println);
    }
}
