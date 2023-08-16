import java.util.Arrays;
import java.util.Scanner;

public class ArrayModifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] arr = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(e -> Integer.parseInt(e))
                .toArray();

        String input = scanner.nextLine();
        while (!input.equals("end")) {

            if (input.contains("swap")) {
                int index1 = Integer.parseInt(input.split(" ")[1]);
                int index2 = Integer.parseInt(input.split(" ")[2]);
                int firstNum = arr[index1];
                int secondNum = arr[index2];
                arr[index1] = secondNum;
                arr[index2] = firstNum;

            } else if (input.contains("multiply")) {
                int index1 = Integer.parseInt(input.split(" ")[1]);
                int index2 = Integer.parseInt(input.split(" ")[2]);
                int firstNum = arr[index1];
                int secondNum = arr[index2];
                int product = firstNum * secondNum;
                arr[index1] = product;

            } else if (input.equals("decrease")) {
                for (int i = 0; i < arr.length; i++) {
                    arr[i]--;
                }
            }
            input = scanner.nextLine();
        }
        //Alternative way
        //System.out.println(Arrays.toString(arr).replace("[", "").replace("]", ""));
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + "," + " ");
        }
        System.out.println(arr[arr.length - 1]);

    }
}
