import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _06_StringMatrixRotation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rotation = Integer.parseInt(scanner.nextLine()
                .split("\\(")[1]
                .replaceAll("\\)", ""));

        int rotationAngle = rotation % 360;

        String input = scanner.nextLine();

        List<String> words = new ArrayList<>();
        int maxLength = Integer.MIN_VALUE;

        while (!"END".equals(input)) {
            words.add(input);
            if (input.length() > maxLength) {
                maxLength = input.length();
            }
            input = scanner.nextLine();
        }

        int rows = words.size();
        int cols = maxLength;

        char[][] matrix = new char[rows][cols];

        fillMatrix(matrix, words);


        switch (rotationAngle) {
            case 90:
                matrix = rotate90(matrix);
                break;
            case 180:
                matrix = rotate90(matrix);
                matrix = rotate90(matrix);
                break;
            case 270:
                matrix = rotate90(matrix);
                matrix = rotate90(matrix);
                matrix = rotate90(matrix);
                break;
        }

        printMatrix(matrix);
    }

    private static char[][] rotate90(char[][] matrix) {
        int rows = matrix[0].length;
        int cols = matrix.length;

        char[][] rotatedMatrix = new char[rows][cols];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                rotatedMatrix[j][(cols - 1) - i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static void fillMatrix(char[][] matrix, List<String> words) {
        for (int row = 0; row < matrix.length; row++) {
            String currentWord = words.get(row);
            for (int col = 0; col < matrix[row].length; col++) {
                if (col < currentWord.length()) {
                    matrix[row][col] = currentWord.charAt(col);
                } else {
                    matrix[row][col] = ' ';
                }
            }
        }
    }
}
