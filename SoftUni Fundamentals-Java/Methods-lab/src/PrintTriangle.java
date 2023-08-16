import java.util.Scanner;

public class PrintTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputNum = Integer.parseInt(scanner.nextLine());
        printTriangle(inputNum);
    }

    public static void printTriangle(int num) {
        for (int i = 1; i <= num; i++) {
            printSingleLine(i);
        }
        for (int i = num - 1; i >= 1; i--) {
            printSingleLine(i);
        }
    }

    public static void printSingleLine (int end) {
        for (int i = 1; i <= end; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
