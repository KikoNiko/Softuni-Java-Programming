package april1322;

import java.util.Scanner;

public class Armory {

    static int aRow;
    static int aCol;
    static int coins = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] armory = fillMatrix(scanner, n);

        boolean inProgress = true;
        while (inProgress) {
            String direction = scanner.nextLine();
            armory[aRow][aCol] = '-';
            if (direction.equals("up")) {
                inProgress = move(armory, aRow - 1, aCol);
            } else if(direction.equals("down")) {
                inProgress = move(armory, aRow + 1, aCol);
            } else if(direction.equals("left")) {
                inProgress = move(armory, aRow, aCol - 1);
            } else if(direction.equals("right")) {
                inProgress = move(armory, aRow, aCol + 1);
            }
        }

        System.out.printf("The king paid %d gold coins.%n", coins);

        printMatrix(armory);
    }


    private static boolean move(char[][] matrix, int row, int col) {
        if (!isInBounds(matrix, row, col)) {
            System.out.println("I do not need more swords!");
            return false;
        }

        aRow = row;
        aCol = col;

        if (Character.isDigit(matrix[row][col])) {
            coins += Integer.parseInt(String.valueOf(matrix[row][col]));
            matrix[row][col] = 'A';
            if (coins >= 65) {
                System.out.println("Very nice swords, I will come back for more!");
                return false;
            }
        } else if (matrix[row][col] == 'M') {
            matrix[row][col] = '-';
            moveToMirror(matrix);
        }

        return true;
    }

    private static void moveToMirror(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                boolean isSame = aRow == r && aCol == c;
                if (matrix[r][c] == 'M' && !isSame) {
                    aRow = r;
                    aCol = c;
                    matrix[aRow][aCol] = 'A';
                }
            }
        }
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static char[][] fillMatrix(Scanner scanner, int rows) {
        char[][] matrix = new char[rows][];

        for (int r = 0; r < matrix.length; r++) {
            matrix[r] = scanner.nextLine().toCharArray();
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 'A') {
                    aRow = r;
                    aCol = c;
                }
            }
        }
        return matrix;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(row);
        }
    }
}
