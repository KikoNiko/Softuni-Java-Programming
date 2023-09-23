import java.util.Arrays;
import java.util.Scanner;

public class _03_IntersectionOfTwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        char[][] firstMatrix = new char[rows][];
        char[][] secondMatrix = new char[rows][];

        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            firstMatrix[i] = line.toCharArray();
        }

        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            secondMatrix[i] = line.toCharArray();
        }

        for (int r = 0; r < firstMatrix.length; r++) {
            for (int c = 0; c < firstMatrix[r].length; c++) {
                char symbol = '*';
                if (firstMatrix[r][c] == secondMatrix[r][c]) {
                    symbol = firstMatrix[r][c];
                }
                System.out.print(symbol);
            }
            System.out.println();
        }

    }
}
