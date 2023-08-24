import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> numList = Arrays.stream(scanner.nextLine()
                        .split(" "))
                .collect(Collectors.toList());

        String[] bomb = scanner.nextLine().split(" ");
        String bombNum = bomb[0];
        int power = Integer.parseInt(bomb[1]);

        while (numList.contains(bombNum)) {
            int bombIndex = numList.indexOf(bombNum);

            int left = Math.max(0, bombIndex - power);
            int right = Math.min(bombIndex + power, numList.size() - 1);

            for (int i = right; i >= left; i--) {
                numList.remove(i);
            }
        }

        System.out.println(numList.stream().mapToInt(Integer::parseInt).sum());
    }
}
