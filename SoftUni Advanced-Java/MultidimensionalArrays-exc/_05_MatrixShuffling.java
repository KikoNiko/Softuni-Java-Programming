import java.util.Scanner;

public class _05_MatrixShuffling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(tokens[0]);
        int cols = Integer.parseInt(tokens[1]);

        String[][] matrix = readMatrix(scanner, rows, cols);

        String command = scanner.nextLine();
        while (!command.equals("END")) {
            String[] commandArgs = command.split("\\s+");
            if (!isValid(commandArgs, rows, cols)) {
                System.out.println("Invalid input!");
                command = scanner.nextLine();
                continue;
            }

            int row1 = Integer.parseInt(commandArgs[1]);
            int col1 = Integer.parseInt(commandArgs[2]);
            int row2 = Integer.parseInt(commandArgs[3]);
            int col2 = Integer.parseInt(commandArgs[4]);

            String temp = matrix[row1][col1];
            matrix[row1][col1] = matrix[row2][col2];
            matrix[row2][col2] = temp;

            printMatrix(matrix);

            command = scanner.nextLine();
        }

    }


    private static void printMatrix(String[][] matrix) {

        for (String[] arr : matrix) {
            for (String str : arr) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValid(String[] args, int rows, int cols) {
        if (args.length != 5 || !args[0].equals("swap")) {
            return false;
        }

        int row1 = Integer.parseInt(args[1]);
        int col1 = Integer.parseInt(args[2]);
        int row2 = Integer.parseInt(args[3]);
        int col2 = Integer.parseInt(args[4]);

        boolean areIndexesValid = row1 < rows && col1 < cols && row1 >= 0 && col1 >= 0 &&
                row2 < rows && col2 < cols && row2 >= 0 && col2 >= 0;

        return areIndexesValid;
    }

    private static String[][] readMatrix(Scanner scanner, int rows, int cols) {

        String[][] matrix = new String[rows][cols];

        for (int r = 0; r < matrix.length; r++) {
            matrix[r] = scanner.nextLine().split("\\s+");
        }

        return matrix;
    }
}
