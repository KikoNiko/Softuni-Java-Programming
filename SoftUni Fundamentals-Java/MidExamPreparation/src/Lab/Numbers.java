package Lab;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] numArr = Arrays.stream(scanner.nextLine()
                        .split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        Arrays.sort(numArr);
        //sort the array in descending order
        //storing it in resultArr
        int [] resultArr = new int[numArr.length];
        int index = 0;
        for (int i = numArr.length - 1; i >= 0; i--) {
            resultArr[index] = numArr[i];
            index++;
        }
        numArr = resultArr;

        double average = avgOfNumArray(numArr);

        int count = 0;
        for (int n : resultArr) {
            if (n == 0) break;
            if (count == 5) break;
            if (n > average) {
                System.out.print(n + " ");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No");
        }
    }

    public static double avgOfNumArray(int [] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum / arr.length;
    }
}
