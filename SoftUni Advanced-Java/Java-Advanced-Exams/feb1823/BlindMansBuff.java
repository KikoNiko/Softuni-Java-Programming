package feb1823;

import java.util.Scanner;

public class BlindMansBuff {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split(" ");
        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);

        char[][] playground = new char[n][m];
        int bRow = -1;
        int bCol = -1;

        for (int r = 0; r < playground.length; r++) {
            playground[r] = scanner.nextLine().replaceAll(" ", "").toCharArray();
            for (int c = 0; c < playground[r].length; c++) {;
                if (playground[r][c] =='B') {
                    bRow = r;
                    bCol = c;
                }
            }
        }

        int movesCount = 0;
        int touchedPlayers = 0;

        String command = scanner.nextLine();
        while (!command.equals("Finish")) {
            switch (command) {
                case "up":
                    if (isValidMove(playground, bRow - 1, bCol)) {
                        playground[bRow][bCol] = '-';
                        bRow -= 1;
                        if (playground[bRow][bCol] == 'P') {
                            touchedPlayers++;
                        }
                        playground[bRow][bCol] = 'B';
                        movesCount++;
                    }
                    break;
                case "down":
                    if (isValidMove(playground, bRow + 1, bCol)) {
                        playground[bRow][bCol] = '-';
                        bRow += 1;
                        if (playground[bRow][bCol] == 'P') {
                            touchedPlayers++;
                        }
                        playground[bRow][bCol] = 'B';
                        movesCount++;
                    }
                    break;
                case "left":
                    if (isValidMove(playground, bRow, bCol - 1)) {
                        playground[bRow][bCol] = '-';
                        bCol -= 1;
                        if (playground[bRow][bCol] == 'P') {
                            touchedPlayers++;
                        }
                        playground[bRow][bCol] = 'B';
                        movesCount++;
                    }
                    break;
                case "right":
                    if (isValidMove(playground, bRow, bCol + 1)) {
                        playground[bRow][bCol] = '-';
                        bCol += 1;
                        if (playground[bRow][bCol] == 'P') {
                            touchedPlayers++;
                        }
                        playground[bRow][bCol] = 'B';
                        movesCount++;
                    }
                    break;
            }
            if (touchedPlayers == 3) {
                break;
            }

            command = scanner.nextLine();
        }

        System.out.println("Game over!");
        //Touched opponents: {count} Moves made: {count}"
        System.out.printf("Touched opponents: %d Moves made: %d", touchedPlayers, movesCount);

    }


    private static boolean isValidMove(char[][] matrix, int row, int col) {
        return isInBounds(matrix, row, col) && !obsticleAhead(matrix, row, col);
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static boolean obsticleAhead(char[][] matrix, int row, int col) {
        return matrix[row][col] == 'O';
    }

}
