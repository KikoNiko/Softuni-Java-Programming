import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GaussTrick {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 0; i < numList.size() - 1; i++) {
            int lastIndex = numList.size() - 1;
            int sum = numList.get(i) + numList.get(lastIndex);
            numList.set(i, sum);
            numList.remove(lastIndex);
        }
        for (int el : numList) {
            System.out.print(el + " ");
        }
    }
}
