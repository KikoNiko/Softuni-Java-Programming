import java.util.*;

public class _08_WrongMeasurements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = readMatrix(scanner);

        List<int[]> correctedValues = new ArrayList<>();

        int[] indexes = readArray(scanner);
        int wrongValue = matrix[indexes[0]][indexes[1]];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == wrongValue) {
                    int sum = 0;
                    if (isInBounds(matrix, r - 1, c) && matrix[r - 1][c] != wrongValue) {
                        sum += matrix[r - 1][c];
                    }
                    if (isInBounds(matrix, r, c - 1) && matrix[r][c - 1] != wrongValue) {
                        sum += matrix[r][c - 1];
                    }
                    if (isInBounds(matrix, r + 1, c) && matrix[r + 1][c] != wrongValue) {
                        sum += matrix[r + 1][c];
                    }
                    if (isInBounds(matrix, r, c + 1) && matrix[r][c + 1] != wrongValue) {
                        sum += matrix[r][c + 1];
                    }
                    int[] params = new int[3];
                    params[0] = r;
                    params[1] = c;
                    params[2] = sum;

                    correctedValues.add(params);
                }
            }
        }

        for (int[] params : correctedValues) {
            matrix[params[0]][params[1]] = params[2];
        }
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] arr : matrix) {
            for (int e : arr) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(int[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length
                && c >= 0 && c < matrix[r].length;
    }

    private static int[][] readMatrix(Scanner scanner) {
        int size = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[size][];

        for (int i = 0; i < size; i++) {
            int[] arr = readArray(scanner);

            matrix[i] = arr;
        }
        return matrix;
    }

    private static int[] readArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
