import java.util.Scanner;

public class ReverseArrayOfStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strArr = scanner.nextLine().split(" ");

        for (int i = strArr.length - 1; i >= 0; i--) {
            System.out.print(strArr[i] + " ");
        }
    }
}
