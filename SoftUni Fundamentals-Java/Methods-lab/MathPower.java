import java.text.DecimalFormat;
import java.util.Scanner;

public class MathPower {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double num = Double.parseDouble(scanner.nextLine());
        int power = Integer.parseInt(scanner.nextLine());

        double result = raiseToPower(num, power);
        System.out.println(new DecimalFormat("0.####").format(result));

    }

    public static double raiseToPower(double num, int power) {

        return Math.pow(num, power);
    }
}
