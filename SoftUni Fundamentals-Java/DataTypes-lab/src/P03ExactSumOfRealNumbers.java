import java.math.BigDecimal;
import java.util.Scanner;

public class P03ExactSumOfRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        BigDecimal bigSum = new BigDecimal(0);

        for (int i = 1; i <= n; i++) {
            BigDecimal num = new BigDecimal(scanner.nextLine());
            bigSum = bigSum.add(num);
        }

        System.out.println(bigSum);
    }
}
