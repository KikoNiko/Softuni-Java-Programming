package dec1521;

import java.util.Scanner;

public class ThroneConquering {

    static int pRow;
    static int pCol;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int energy = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = createMatrix(n, scanner);

        while (energy > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            int spawnRow = Integer.parseInt(tokens[1]);
            int spawnCol = Integer.parseInt(tokens[2]);
            matrix[spawnRow][spawnCol] = 'S';

            move(matrix, tokens[0]);
            energy--;

            if (matrix[pRow][pCol] == 'S') {
                energy -= 2;
                if (energy > 0) {
                    matrix[pRow][pCol] = '-';
                }
            } else if (matrix[pRow][pCol] == 'H') {
                matrix[pRow][pCol] = '-';
                break;
            }

        }

        if (energy <= 0) {
            matrix[pRow][pCol] = 'X';
            System.out.printf("Paris died at %d;%d.%n", pRow, pCol);
        } else {
            System.out.println("Paris has successfully abducted Helen! Energy left: " + energy);
        }

        printMatrix(matrix);
    }


    private static void move(char[][] matrix, String direction) {
        if (direction.equals("up")) {
            if (isInBounds(matrix, pRow - 1, pCol)) {
                pRow--;
            }
        } else if (direction.equals("down")) {
            if (isInBounds(matrix, pRow + 1, pCol)) {
                pRow++;
            }
        } else if (direction.equals("left")) {
            if (isInBounds(matrix, pRow, pCol - 1)) {
                pCol--;
            }
        } else if (direction.equals("right")) {
            if (isInBounds(matrix, pRow, pCol + 1)) {
                pCol++;
            }
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(row);
        }
    }

    private static char[][] createMatrix(int n, Scanner scanner) {
        char[][] matrix = new char[n][];
        for (int r = 0; r < n; r++) {
            matrix[r] = scanner.nextLine().toCharArray();
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 'P') {
                    pRow = r;
                    pCol = c;
                    matrix[r][c] = '-';
                }
            }
        }
        return matrix;
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
