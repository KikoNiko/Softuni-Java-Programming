package oct2222;

import java.util.Scanner;

public class RallyRacing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String carNumber = scanner.nextLine();

        String[][] matrix = fillMatrix(scanner, n);

        int carRow = 0;
        int carCol = 0;
        int distance = 0;
        boolean hasFinished = false;

        String direction = scanner.nextLine();
        while (!direction.equals("End")) {
            if (direction.equals("down")) {
                carRow++;
            } else if (direction.equals("right")) {
                carCol++;
            } else if (direction.equals("up")) {
                carRow--;
            } else if (direction.equals("left")) {
                carCol--;
            }
            distance += 10;
            if (matrix[carRow][carCol].equals("T")) {
                matrix[carRow][carCol] = ".";
                for (int r = 0; r < matrix.length; r++) {
                    for (int c = 0; c < matrix[r].length; c++) {
                        if (matrix[r][c].equals("T")) {
                            carRow = r;
                            carCol = c;
                            matrix[carRow][carCol] = ".";
                            break;
                        }
                    }
                }
                distance += 20;
            } else if (matrix[carRow][carCol].equals("F")) {
                hasFinished = true;
                break;
            }

            direction = scanner.nextLine();
        }

        if (hasFinished) {
            System.out.printf("Racing car %s finished the stage!%n", carNumber);
        } else {
            System.out.printf("Racing car %s DNF.%n", carNumber);
        }
        System.out.printf("Distance covered %d km.%n", distance);

        matrix[carRow][carCol] = "C";
        printMatrix(matrix);
    }


    private static void printMatrix(String[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c]);
            }
            System.out.println();
        }
    }

    private static String[][] fillMatrix(Scanner scanner, int n) {
        String[][] matrix = new String[n][];
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().split("\\s+");
        }
        return matrix;
    }
}
