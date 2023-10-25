package oct2321;

import java.util.Scanner;

public class MouseAndCheese {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[n][];

        int mRow = -1;
        int mCol = -1;

        for (int row = 0; row < n; row++) {
            matrix[row] = scanner.nextLine().toCharArray();
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'M') {
                    mRow = row;
                    mCol = col;
                    matrix[row][col] = '-';
                }
            }
        }

        String command = scanner.nextLine();
        int cheeseCount = 0;
        int result;

        while (!command.equals("end")) {
            matrix[mRow][mCol] = '-';
            switch (command) {
                case "up":
                    if (!isInBounds(matrix, mRow - 1, mCol)) {
                        System.out.println("Where is the mouse?");
                        break;
                    }
                    mRow--;
                    result = move(matrix, mRow, mCol);
                    if (result == -1) {
                        mRow--;
                        result = move(matrix, mRow, mCol);
                    }
                    cheeseCount += result;
                    break;
                case "down":
                    if (!isInBounds(matrix, mRow + 1, mCol)) {
                        System.out.println("Where is the mouse?");
                        break;
                    }
                    mRow++;
                    result = move(matrix, mRow, mCol);
                    if (result == -1) {
                        mRow++;
                        result = move(matrix, mRow, mCol);
                    }
                    cheeseCount += result;
                    break;
                case "left":
                    if (!isInBounds(matrix, mRow, mCol - 1)) {
                        System.out.println("Where is the mouse?");
                        break;
                    }
                    mCol--;
                    result = move(matrix, mRow, mCol);
                    if (result == -1) {
                        mCol--;
                        result = move(matrix, mRow, mCol);
                    }
                    cheeseCount += result;
                    break;
                case "right":
                    if (!isInBounds(matrix, mRow, mCol + 1)) {
                        System.out.println("Where is the mouse?");
                        break;
                    }
                    mCol++;
                    result = move(matrix, mRow, mCol);
                    if (result == -1) {
                        mCol++;
                        result = move(matrix, mRow, mCol);
                    }
                    cheeseCount += result;
                    break;
            }

            command = scanner.nextLine();
        }

        if (cheeseCount < 5) {
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.%n", 5 - cheeseCount);
        } else {
            System.out.printf("Great job, the mouse is fed %d cheeses!%n", cheeseCount);
        }

        printMatrix(matrix);

    }


    private static int move(char[][] matrix, int row, int col) {
        if (matrix[row][col] == 'c') {
            matrix[row][col] = 'M';
            return 1;
        } else if (matrix[row][col] == 'B') {
            matrix[row][col] = '-';
            return -1;
        } else {
            matrix[row][col] = 'M';
        }
        return 0;
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >=0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
