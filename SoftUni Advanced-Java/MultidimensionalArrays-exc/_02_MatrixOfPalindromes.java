import java.util.Arrays;
import java.util.Scanner;

public class _02_MatrixOfPalindromes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] input = readIntegerArray(scanner);
        int rows = input[0];
        int cols = input[1];

        String[][] matrix = fillMatrix(rows, cols);
        printMatrix(matrix);
    }


    private static void printMatrix(String[][] matrix) {

        for (String[] arr : matrix) {
            for (String str : arr) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }

    private static String[][] fillMatrix(int r, int c) {
        String[][] matrix = new String[r][c];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                String firstSymbol = String.valueOf((char) ('a' + row));
                String midSymbol = String.valueOf((char) ('a' + row + col));
                matrix[row][col] = firstSymbol + midSymbol + firstSymbol;
            }
        }
        
        return matrix;
    }

    private static int[] readIntegerArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
