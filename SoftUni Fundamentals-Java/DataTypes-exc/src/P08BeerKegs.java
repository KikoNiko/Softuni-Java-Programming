import java.util.Scanner;

public class P08BeerKegs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        double biggestVolume = 0;
        String biggestModel = "";
        for (int i = 1; i <= n; i++) {
            String model = scanner.nextLine();
            double radius = Double.parseDouble(scanner.nextLine());
            int height = Integer.parseInt(scanner.nextLine());
            double volume = Math.PI * radius * radius * height;
            if (volume > biggestVolume) {
                biggestVolume = volume;
                biggestModel = model;
            }
        }
        System.out.println(biggestModel);

    }
}
