package june2522;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class StickyFingers {

    static int dRow;
    static int dCol;
    static int pocket;
    static boolean isCaught = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Deque<String> directionsQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(","))
                .forEach(directionsQueue::offer);

        char[][] matrix = new char[n][];
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().replaceAll(" ", "").toCharArray();
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'D') {
                    dRow = row;
                    dCol = col;
                }
            }
        }

        boolean inProgress = true;
        while (inProgress) {
            String direction = directionsQueue.poll();
            if (direction.equals("up")) {
                inProgress = move(matrix, dRow - 1, dCol);
            } else if (direction.equals("down")) {
                inProgress = move(matrix, dRow + 1, dCol);
            } else if (direction.equals("left")) {
                inProgress = move(matrix, dRow, dCol - 1);
            } else if (direction.equals("right")) {
                inProgress = move(matrix, dRow, dCol + 1);
            }

            if (isCaught) {
                break;
            }
            if (directionsQueue.isEmpty()) {
                System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", pocket);
                inProgress = false;
            }
        }

        printMatrix(matrix);

    }


    private static boolean move(char[][] matrix, int row, int col) {
        if (!isInBounds(matrix, row, col)) {
            System.out.println("You cannot leave the town, there is police outside!");
            return true;
        }
        matrix[dRow][dCol] = '+';
        dRow = row;
        dCol = col;

        if (matrix[row][col] == '$') {
            matrix[row][col] = '+';
            int amount = row * col;
            System.out.printf("You successfully stole %d$.%n", amount);
            pocket += amount;
        } else if (matrix[row][col] == 'P') {
            System.out.printf("You got caught with %d$, and you are going to jail.%n", pocket);
            matrix[row][col] = '#';
            isCaught = true;
            return false;
        }
        matrix[dRow][dCol] = 'D';
        return true;
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static void printMatrix(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }
}

