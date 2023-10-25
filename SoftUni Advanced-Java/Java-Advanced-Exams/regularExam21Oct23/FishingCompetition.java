package regularExam21Oct23;

import java.util.Scanner;

public class FishingCompetition {

    static int sRow;
    static int sCol;
    static int fishCaught = 0;
    static boolean inWhirlpool = false;
    static boolean quotaReached = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[n][];
        for (int r = 0; r < matrix.length; r++) {
            matrix[r] = scanner.nextLine().toCharArray();
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 'S') {
                    sRow = r;
                    sCol = c;
                }
            }
        }

        boolean inProgress = true;
        while (inProgress) {
            String command = scanner.nextLine();

            if (command.equals("up")) {
                inProgress = move(matrix, sRow - 1, sCol);
            } else if (command.equals("down")) {
                inProgress = move(matrix, sRow + 1, sCol);
            } else if (command.equals("left")) {
                inProgress = move(matrix, sRow, sCol - 1);
            } else if (command.equals("right")) {
                inProgress = move(matrix, sRow, sCol + 1);
            } else if (command.equals("collect the nets")) {
                break;
            }

        }

        if (!inWhirlpool) {
            if (!quotaReached) {
                System.out.printf("You didn't catch enough fish and didn't reach the quota! You need %d tons of fish more.%n", 20 - fishCaught);
            } else {
                System.out.println("Success! You managed to reach the quota!");
            }
            if (fishCaught > 0) {
                System.out.printf("Amount of fish caught: %d tons.%n", fishCaught);
            }

            printMatrix(matrix);
        } else {
            System.out.printf("You fell into a whirlpool! The ship sank and you lost the fish you caught. Last coordinates of the ship: [%d,%d]", sRow, sCol);
        }
        //printMatrix(matrix);
    }


    private static boolean move(char[][] matrix, int row, int col) {
        if (row == -1) {
            row = matrix.length - 1;
        } else if (row == matrix.length) {
            row = 0;
        }
        if (col == -1) {
            col = matrix.length - 1;
        } else if (col == matrix.length) {
            col = 0;
        }
        matrix[sRow][sCol] = '-';

        sRow = row;
        sCol = col;

        if (Character.isDigit(matrix[row][col])) {
            fishCaught += Integer.parseInt(String.valueOf(matrix[row][col]));
            matrix[row][col] = '-';
            if (fishCaught >= 20) {
                quotaReached = true;
            }
        }
        if (matrix[row][col] == 'W') {
            inWhirlpool = true;
            return false;
        }

        matrix[sRow][sCol] = 'S';
        return true;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(row);
        }
    }
}
