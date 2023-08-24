import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftUniBarIncome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = "%(?<name>[A-z][a-z]+)%[^|$%.]*<(?<product>\\w+)>[^|$%.]*\\|(?<count>[0-9]+)\\|[^|$%.]*?(?<price>[0-9]+\\.?[0-9]*)\\$";
        double totalIncome = 0;

        String input = scanner.nextLine();
        while (!input.equals("end of shift")) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String name = matcher.group("name");
                String product = matcher.group("product");
                int count = Integer.parseInt(matcher.group("count"));
                double price = Double.parseDouble(matcher.group("price"));

                double productValue = count * price;
                System.out.printf("%s: %s - %.2f%n", name, product, productValue);

                totalIncome += productValue;
            }

            input = scanner.nextLine();
        }

        System.out.printf("Total income: %.2f", totalIncome);
    }
}
