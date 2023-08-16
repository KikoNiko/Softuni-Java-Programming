package MoreExc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdAstra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        String regex = "([#|])(?<itemName>[A-Za-z ]+)\\1(?<date>\\d{2}\\/\\d{2}\\/\\d{2})\\1(?<calories>\\d+)\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int totalCalories = 0;
        List<String> items = new ArrayList<>();

        while (matcher.find()) {
            String itemName = matcher.group("itemName");
            String expirationDate = matcher.group("date");
            int calories = Integer.parseInt(matcher.group("calories"));
            totalCalories += calories;
            items.add(String.format("Item: %s, Best before: %s, Nutrition: %d", itemName, expirationDate, calories));
        }

        int days = totalCalories / 2000;
        System.out.printf("You have food to last you for: %d days!%n", days);

        for (String item : items) {
            System.out.println(item);
        }

    }
}
