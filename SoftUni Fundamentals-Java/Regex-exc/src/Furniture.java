import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String regex = ">>(?<furnitureName>[A-Za-z]+)<<(?<price>[-+]?[0-9]*\\.?[0-9]+)!(?<quantity>\\d+)";
        //">>{furniture name}<<{price}!{quantity}
        List<String> furniture = new ArrayList<>();
        double moneySpent = 0;

        while (!input.equals("Purchase")) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String furnitureName = matcher.group("furnitureName");
                String price = matcher.group("price");
                String quantity = matcher.group("quantity");

                furniture.add(furnitureName);
                double cost = Double.parseDouble(price);
                int amount = Integer.parseInt(quantity);
                moneySpent += cost * amount;
            }

            input = scanner.nextLine();
        }

        System.out.println("Bought furniture:");
        for (String name : furniture) {
            System.out.println(name);
        }
        System.out.printf("Total money spend: %.2f", moneySpent);
    }
}
