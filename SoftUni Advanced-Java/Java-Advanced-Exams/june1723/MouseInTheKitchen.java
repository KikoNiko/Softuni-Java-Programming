package june1723;

import java.util.Arrays;
import java.util.Scanner;

public class MouseInTheKitchen {

    public static int mouseRow = 0;
    public static int mouseCol = 0;
    public static int cheeseCount = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = dimensions[0];
        int m = dimensions[1];

        char[][] matrix = new char[n][m];

        for (int row = 0; row < n; row++) {
            matrix[row] = scanner.nextLine().toCharArray();
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'M') {
                    mouseRow = row;
                    mouseCol = col;
                } else if (matrix[row][col] == 'C') {
                    cheeseCount++;
                }
            }
        }

        boolean inProgress = true;

        while (inProgress) {
            String direction = scanner.nextLine();

            matrix[mouseRow][mouseCol] = '*';

            if (direction.equals("danger")) {
                if (cheeseCount > 0) {
                    System.out.println("Mouse will come back later!");
                    matrix[mouseRow][mouseCol] = 'M';
                    break;
                }
                break;
            }

            switch (direction) {
                case "up":
                    inProgress = move(matrix, mouseRow - 1, mouseCol);
                    break;
                case "down":
                    inProgress = move(matrix, mouseRow + 1, mouseCol);
                    break;
                case "left":
                    inProgress = move(matrix, mouseRow, mouseCol - 1);
                    break;
                case "right":
                    inProgress = move(matrix, mouseRow, mouseCol + 1);
                    break;
            }

            matrix[mouseRow][mouseCol] = 'M';

        }

        printMatrix(matrix);

    }

    private static boolean move(char[][] matrix, int nextRow, int nextCol) {
        if (!isInBounds(matrix, nextRow, nextCol)) {
            System.out.println("No more cheese for tonight!");
            return false;
        }
        if (matrix[nextRow][nextCol] == '@') {
            return true;
        }

        mouseRow = nextRow;
        mouseCol = nextCol;

        if (matrix[nextRow][nextCol] == 'C') {
            matrix[nextRow][nextCol] = '*';
            cheeseCount--;
            if (cheeseCount == 0) {
                System.out.println("Happy mouse! All the cheese is eaten, good night!");
                return false;
            }
        }

        if (matrix[nextRow][nextCol] == 'T') {
            System.out.println("Mouse is trapped!");
            return false;
        }

        return true;
    }

    private static void printMatrix(char[][] matrix) {

        for (char[] arr : matrix) {
            for (char c : arr) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(char[][] matrix, int r, int c) {
        return  r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length;
    }
}
