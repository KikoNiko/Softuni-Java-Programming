import java.util.Scanner;

public class P07WaterOverflow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int sumLiters = 0;
        for (int i = 1; i <= n; i++) {
            int quantity = Integer.parseInt(scanner.nextLine());
            if (sumLiters + quantity > 255) {
                System.out.println("Insufficient capacity!");
                continue;
            }
            sumLiters += quantity;
        }
        System.out.println(sumLiters);
    }
}
