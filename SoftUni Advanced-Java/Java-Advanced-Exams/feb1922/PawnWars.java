package feb1922;

import java.util.Scanner;

public class PawnWars {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wRow = -1;
        int wCol = -1;
        int bRow = -1;
        int bCol = -1;

        char[][] board = new char[8][8];
        for (int row = 0; row < board.length; row++) {
            board[row] = scanner.nextLine().toCharArray();
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'w') {
                    wRow = row;
                    wCol = col;
                } else if (board[row][col] == 'b') {
                    bRow = row;
                    bCol = col;
                }
            }
        }

        boolean isCaptured = false;
        while (wRow != 0 && bRow != 7 && !isCaptured) {

            if (whitePawnHitBlack(wRow, wCol, bRow, bCol)) {
                String coordinates = findCoordinates(bRow, bCol);
                System.out.printf("Game over! White capture on %s.", coordinates);
                isCaptured = true;
            }
            wRow--;

            if (blackPawnHitWhite(wRow, wCol, bRow, bCol)) {
                String coordinates = findCoordinates(wRow, wCol);
                System.out.printf("Game over! Black capture on %s.", coordinates);
                isCaptured = true;
            }
            bRow++;
        }

        if (!isCaptured) {
            System.out.print(wRow == 0
                    ? "Game over! White pawn is promoted to a queen at " + findCoordinates(wRow, wCol) + "."
                    : "Game over! Black pawn is promoted to a queen at " + findCoordinates(bRow, bCol) + "."
            );
        }


    }

    private static boolean blackPawnHitWhite(int wRow, int wCol, int bRow, int bCol) {
        return bRow + 1 == wRow && (bCol + 1 == wCol || bCol - 1 == wCol);
    }

    private static boolean whitePawnHitBlack(int wRow, int wCol, int bRow, int bCol) {
        return wRow - 1 == bRow && (wCol + 1 == bCol || wCol - 1 == bCol);
    }


    private static String findCoordinates(int row, int col) {
        char[] cols = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] rows = new char[]{'8', '7', '6', '5', '4', '3', '2', '1'};
        return String.valueOf(cols[col]) + rows[row];
    }

}
