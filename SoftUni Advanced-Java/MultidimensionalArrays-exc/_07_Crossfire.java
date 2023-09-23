import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _07_Crossfire {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(tokens[0]);
        int cols = Integer.parseInt(tokens[1]);

        List<List<Integer>> matrix = new ArrayList<>();

        fillMatrix(matrix, rows, cols);

        String input = scanner.nextLine();
        while (!input.equals("Nuke it from orbit")) {

            String[] inputParts = input.split("\\s+");
            int row = Integer.parseInt(inputParts[0]);
            int col = Integer.parseInt(inputParts[1]);
            int radius = Integer.parseInt(inputParts[2]);

            //destroy up, down
            for (int currentRow = row - radius; currentRow <= row + radius ; currentRow++) {
                if(isInMatrix(currentRow, col, matrix)) { //валидираме реда от който ще вземем
                    matrix.get(currentRow).remove(col);
                }
            }

            //destroy left. right
            for (int currentColumn = col + radius; currentColumn >=  col - radius; currentColumn--) {
                if(isInMatrix(row, currentColumn, matrix)) {
                    matrix.get(row).remove(currentColumn);
                }
            }

            matrix.removeIf(List::isEmpty);

            input = scanner.nextLine();
        }


        printMatrix(matrix);

    }



    private static boolean isInMatrix(int row, int col, List<List<Integer>> matrix) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }

    private static void printMatrix(List<List<Integer>> matrix) {
        for (List<Integer> row : matrix) {
            row.forEach(e -> System.out.print(e + " "));
            System.out.println();
        }
    }

    private static void fillMatrix(List<List<Integer>> matrix, int rows, int cols) {
        int count = 1;
        for (int row = 0; row < rows; row++) {
            List<Integer> numbers = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                numbers.add(count++);
            }
            matrix.add(numbers);
        }
    }

}
