import java.util.Scanner;

public class PCStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double processorPrice = Double.parseDouble(scanner.nextLine());
        double gCardPrice = Double.parseDouble(scanner.nextLine());
        double ramPrice = Double.parseDouble(scanner.nextLine());
        int numOfRam = Integer.parseInt(scanner.nextLine());
        double discount = Double.parseDouble(scanner.nextLine());

        double discountedProducts = processorPrice - (processorPrice * discount) + gCardPrice - (gCardPrice * discount);
        double total = discountedProducts + numOfRam * ramPrice;

        System.out.printf("Money needed - %.2f leva.", total * 1.57);
    }
}
