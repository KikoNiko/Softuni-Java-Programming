import java.util.Scanner;

public class DeerOfSanta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysGone = Integer.parseInt(scanner.nextLine());
        int foodLeft = Integer.parseInt(scanner.nextLine());
        double firstDeerFood = Double.parseDouble(scanner.nextLine());
        double secondDeerFood = Double.parseDouble(scanner.nextLine());
        double thirdDeerFood = Double.parseDouble(scanner.nextLine());

        double totalFoodNeeded = daysGone * (firstDeerFood + secondDeerFood + thirdDeerFood);

        if (foodLeft >= totalFoodNeeded) {
            System.out.printf("%.0f kilos of food left.", Math.floor(foodLeft - totalFoodNeeded));
        } else {
            System.out.printf("%.0f more kilos of food are needed.", Math.ceil(totalFoodNeeded - foodLeft));
        }
    }
}
