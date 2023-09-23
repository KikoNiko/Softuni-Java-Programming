import java.util.ArrayDeque;
import java.util.Scanner;

public class _03_DecimalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        ArrayDeque<String> binary = new ArrayDeque<>();

        if (number == 0) {
            System.out.println(0);
        }
        while (number > 0) {
            binary.push(number % 2 + "");
            number /= 2;
        }

        binary.forEach(System.out::print);
    }
}
