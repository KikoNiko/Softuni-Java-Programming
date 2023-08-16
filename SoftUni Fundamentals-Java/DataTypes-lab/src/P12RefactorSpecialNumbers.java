import java.util.Scanner;

public class P12RefactorSpecialNumbers {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        boolean isSpecialNum = false;
        for (int ch = 1; ch <= num; ch++) {
            int currentNum = ch;
            int sum = 0;
            while (ch > 0) {
                sum += ch % 10;
                ch = ch / 10;
            }
            isSpecialNum = (sum == 5) || (sum == 7) || (sum == 11);
            if (isSpecialNum) {
                System.out.printf("%d -> True%n", currentNum);
            } else {
                System.out.printf("%d -> False%n", currentNum);
            }
            ch = currentNum;
        }
    }
}
