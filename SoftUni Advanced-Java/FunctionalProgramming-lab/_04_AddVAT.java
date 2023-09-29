import java.util.Arrays;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class _04_AddVAT {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UnaryOperator<String> priceWithVat = s -> String.format("%.2f", Double.parseDouble(s) * 1.2);

        System.out.println("Prices with VAT:");

        Arrays.stream(scanner.nextLine().split(", "))
                .map(priceWithVat)
                .forEach(System.out::println);
    }
}
