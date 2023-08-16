package Lab1;

import java.util.Scanner;

public class AreaOfFigures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String figureType = scanner.nextLine();

        double area = 0;

        if (figureType.equals("square")) {
            double a = Double.parseDouble(scanner.nextLine());
            area = a * a;
            System.out.printf("%.3f", area);
        } else if (figureType.equals("rectangle")){
            double a = Double.parseDouble(scanner.nextLine());
            double b = Double.parseDouble(scanner.nextLine());
            area = a * b;
            System.out.printf("%.3f", area);
        } else if (figureType.equals("circle")){
            double a = Double.parseDouble(scanner.nextLine());
            area = Math.PI * a * a;
            System.out.printf("%.3f", area);
        } else if (figureType.equals("triangle")){
            double a = Double.parseDouble(scanner.nextLine());
            double b = Double.parseDouble(scanner.nextLine());
            area = (a * b) / 2;
            System.out.printf("%.3f", area);
        }
    }
}
