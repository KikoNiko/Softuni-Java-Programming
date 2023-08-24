import java.util.Scanner;

public class SmallestOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int first = Integer.parseInt(scanner.nextLine());
        int second = Integer.parseInt(scanner.nextLine());
        int third = Integer.parseInt(scanner.nextLine());
        getSmallestNum(first, second, third);
    }

    public static void getSmallestNum(int a, int b, int c) {
        int smallest = Math.min(Math.min(a, b), c);
        System.out.println(smallest);
    }
}
