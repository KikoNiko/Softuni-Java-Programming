import java.util.Scanner;

public class P03Vacation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        String type = scanner.nextLine();
        String day = scanner.nextLine();

        double price = 0.00;

        if (type.equals("Students")) {
            if (day.equals("Friday")) {
                price = num * 8.45;
            } else if (day.equals("Saturday")) {
                price = num * 9.80;
            } else {
                price = num * 10.46;
            }
            if (num >= 30) {
                price -= price * 0.15;
            }
        } else if (type.equals("Business")) {
            if (num >= 100) {
                num -= 10;
            }
            if (day.equals("Friday")) {
                price = num * 10.90;
            } else if (day.equals("Saturday")) {
                price = num * 15.60;
            } else {
                price = num * 16.00;
            }
        } else {
            if (day.equals("Friday")) {
                price = num * 15.00;
            } else if (day.equals("Saturday")) {
                price = num * 20.00;
            } else {
                price = num * 22.50;
            }
            if (num >= 10 && num <= 20) {
                price -= price * 0.05;
            }
        }

        System.out.printf("Total price: %.2f", price);
    }
}
