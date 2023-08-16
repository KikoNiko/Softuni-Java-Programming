import java.util.Scanner;

public class ComputerFirm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfComps = Integer.parseInt(scanner.nextLine());
        double sumRating = 0.00;
        double sumSales = 0.00;

        for (int i = 0; i < numOfComps; i++) {
            int salesRating = Integer.parseInt(scanner.nextLine());
            int rating = salesRating % 10;
            sumRating += rating;

            int possibleSales = salesRating / 10;
            double realSales = 0.00;

            switch (rating) {
                case 3 :
                    realSales = possibleSales / 2.0;
                    break;
                case 4 :
                    realSales = possibleSales * 0.70;
                    break;
                case 5 :
                    realSales = possibleSales * 0.85;
                    break;
                case 6 :
                    realSales = possibleSales;
                    break;
            }
            sumSales += realSales;
        }
        System.out.printf("%.2f\n", sumSales);
        System.out.printf("%.2f", sumRating / numOfComps);
    }
}
