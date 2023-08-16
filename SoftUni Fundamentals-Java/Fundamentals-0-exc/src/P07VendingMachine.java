import java.util.Scanner;

public class P07VendingMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        double sum = 0;

        while (!input.equals("Start")) {
            double coins = Double.parseDouble(input);
            if (coins != 0.1 && coins != 0.2 && coins != 0.5 && coins != 1 && coins != 2) {
                System.out.printf("Cannot accept %.2f%n", coins);
            } else {
                sum += coins;
            }
            input = scanner.nextLine();
        }
        String product = scanner.nextLine();

        while (!product.equals("End")) {
            boolean hasMoney = true;
            switch (product) {
                case "Nuts":
                    if (sum < 2) {
                        System.out.println("Sorry, not enough money");
                        hasMoney = false;
                    } else {
                        sum -= 2;
                    }
                    break;
                case "Water":
                    if (sum < 0.7) {
                        System.out.println("Sorry, not enough money");
                        hasMoney = false;
                    } else {
                        sum -= 0.7;
                    }
                    break;
                case "Crisps":
                    if (sum < 1.5) {
                        System.out.println("Sorry, not enough money");
                        hasMoney = false;
                    } else {
                        sum -= 1.5;
                    }
                    break;
                case "Soda":
                    if (sum < 0.8) {
                        System.out.println("Sorry, not enough money");
                        hasMoney = false;
                    } else {
                        sum -= 0.8;
                    }

                    break;
                case "Coke":
                    if (sum < 1) {
                        System.out.println("Sorry, not enough money");
                        hasMoney = false;
                    } else {
                        sum -= 1;
                    }
                    break;
                default:
                    System.out.println("Invalid product");
                    hasMoney = false;
                    break;
            }
            if (!hasMoney) {
                product = scanner.nextLine();
                continue;
            }
            System.out.printf("Purchased %s%n", product);


            product = scanner.nextLine();
        }

        System.out.printf("Change: %.2f", sum);
    }
}
