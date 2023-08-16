import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] inputArr = scanner.nextLine().split(" ");
        int rotations = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= rotations; i++) {
            String firstEl = inputArr[0];

            for (int j = 0; j < inputArr.length - 1; j++) {
                inputArr[j] = inputArr[j + 1];
            }
            inputArr[inputArr.length - 1] = firstEl;
        }
        for (String el : inputArr) {
            System.out.print(el + " ");
        }
    }
}
