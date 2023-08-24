import java.util.Scanner;

public class P10PokeMon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pokePower = Integer.parseInt(scanner.nextLine());
        int distance = Integer.parseInt(scanner.nextLine());
        int exhaustionFactor = Integer.parseInt(scanner.nextLine());

        int targetsPoked = 0;
        int N = pokePower;

        while (N >= distance) {
            N -= distance;
            targetsPoked++;
            if (N == pokePower / 2 && exhaustionFactor != 0) {
                N = N / exhaustionFactor;
            }
        }
        System.out.println(N);
        System.out.println(targetsPoked);
    }
}
