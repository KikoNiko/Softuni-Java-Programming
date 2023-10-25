package feb2220;

import java.util.Scanner;

public class ReVolt {

    static int playerRow;
    static int playerCol;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int commandsCount = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][];
        for (int row = 0; row < size; row++) {
            matrix[row] = scanner.nextLine().toCharArray();
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'f') {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }

        boolean inPlay = true;
        while (commandsCount-- > 0 && inPlay) {
            String command = scanner.nextLine();
            if (command.equals("up")) {
                inPlay = move(matrix, playerRow - 1, playerCol);
                if (matrix[playerRow][playerCol] == 'B') {
                    inPlay = move(matrix, playerRow - 1, playerCol);
                }
            } else if (command.equals("down")) {
                inPlay = move(matrix, playerRow + 1, playerCol);
                if (matrix[playerRow][playerCol] == 'B') {
                    inPlay = move(matrix, playerRow + 1, playerCol);
                }
            } else if (command.equals("left")) {
                inPlay = move(matrix, playerRow, playerCol - 1);
                if (matrix[playerRow][playerCol] == 'B') {
                    inPlay = move(matrix, playerRow, playerCol - 1);
                }
            } else if (command.equals("right")) {
                inPlay = move(matrix, playerRow, playerCol + 1);
                if (matrix[playerRow][playerCol] == 'B') {
                    inPlay = move(matrix, playerRow, playerCol + 1);
                }
            }
        }
        if (!inPlay) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
        }

        printMatrix(matrix);

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

        if (matrix[row][col] == 'T') {
            return true;
        }
        if (matrix[playerRow][playerCol] != 'B') {
            matrix[playerRow][playerCol] = '-';
        }
        playerRow = row;
        playerCol = col;

        if (matrix[row][col] == 'F') {
            matrix[row][col] = 'f';
            return false;
        }
        if (matrix[row][col] != 'B') {
            matrix[row][col] = 'f';
        }
        return true;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(row);
        }
    }

}
