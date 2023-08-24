import java.util.Arrays;
import java.util.Scanner;

public class MultiplyEvensByOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Math.abs(Integer.parseInt(scanner.nextLine()));
        System.out.println(multiplyEvensByOdds(number));

    }

    public static int multiplyEvensByOdds(int n) {
        int evensSum = getEvensSum(n);
        int oddsSum = getOddsSum(n);

        return evensSum * oddsSum;
    }

    public static int getEvensSum(int num) {
        String strNum = String.valueOf(num);
        int[] numArr = Arrays.stream(strNum.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sum = 0;
        for (int n : numArr) {
            if (n % 2 == 0) {
               sum += n;
            }
        }
        return sum;
    }

    public static int getOddsSum(int num) {
        String strNum = String.valueOf(num);
        int[] numArr = Arrays.stream(strNum.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sum = 0;
        for (int n : numArr) {
            if (n % 2 != 0) {
                sum += n;
            }
        }
        return sum;
    }

}
