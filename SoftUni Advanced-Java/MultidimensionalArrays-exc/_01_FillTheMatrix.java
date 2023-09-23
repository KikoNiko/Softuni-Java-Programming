import java.util.Scanner;

public class _01_FillTheMatrix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");
        int size = Integer.parseInt(input[0]);
        String pattern = input[1];

        if (pattern.equals("A")) {
            int[][] matrix = AMatrix(size);
            printMatrix(matrix);
        } else if (pattern.equals("B")) {
            int[][] matrix = BMatrix(size);
            printMatrix(matrix);
        }

    }

    private static void printMatrix(int[][] matrix) {

        for (int[] arr : matrix) {
            for (int e : arr) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    private static int[][] AMatrix(int size) {
        int[][] matrix = new int[size][size];

        for (int r = 0; r < size; r++) {
            int count = r + 1;
            for (int c = 0; c < size; c++) {
                matrix[r][c] = count;
                count += size;
            }
        }
        return matrix;
    }

    private static int[][] BMatrix(int size) {
        int[][] matrix = new int[size][size];
        int count = 1;
        for (int r = 0; r < size; r++) {
            if (r % 2 == 0) {
                for (int c = 0; c < size; c++) {
                    matrix[c][r] = count;
                    count ++;
                }
            } else {
                for (int c = size - 1; c >= 0; c--) {
                    matrix[c][r] = count;
                    count ++;
                }
            }
        }

        return matrix;
    }
}
