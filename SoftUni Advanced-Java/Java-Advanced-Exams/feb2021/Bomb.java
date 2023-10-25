package feb2021;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Bomb {

    static int sRow;
    static int sCol;
    static int bombsCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        Deque<String> directionsQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(","))
                .forEach(directionsQueue::offer);

        String[][] matrix = new String[size][];
        for (int row = 0; row < size; row++) {
            matrix[row] = scanner.nextLine().split(" ");
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("s")) {
                    sRow = row;
                    sCol = col;
                    matrix[row][col] = "+";
                } else if (matrix[row][col].equals("B")) {
                    bombsCount++;
                }
            }
        }

        boolean inProgress = true;
        while (inProgress) {
            if (directionsQueue.isEmpty()) {
                System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)", bombsCount, sRow, sCol);
                break;
            }
            String direction = directionsQueue.poll();
            if (direction.equals("up")) {
                inProgress = move(matrix, sRow - 1, sCol);
            } else if (direction.equals("down")) {
                inProgress = move(matrix, sRow + 1, sCol);
            } else if (direction.equals("left")) {
                inProgress = move(matrix, sRow, sCol - 1);
            } else if (direction.equals("right")) {
                inProgress = move(matrix, sRow, sCol + 1);
            }
        }
    }

    private static boolean move (String[][] matrix, int row, int col) {
        if (!isInBounds(matrix, row, col)) {
            return true;
        }
        sRow = row;
        sCol = col;

        if (matrix[row][col].equals("B")) {
            System.out.println("You found a bomb!");
            matrix[row][col] = "+";
            bombsCount--;
            if (bombsCount == 0) {
                System.out.println("Congratulations! You found all bombs!");
                return false;
            }
        } else if (matrix[row][col].equals("e")) {
            System.out.printf("END! %d bombs left on the field", bombsCount);
            return false;
        }
        return true;
    }

    private static boolean isInBounds(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
