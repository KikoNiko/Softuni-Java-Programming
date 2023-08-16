package Lab;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String barcodeRegex = "@#+[A-Z][A-Za-z0-9]{4,}[A-Z]@#+";

        for (int i = 0; i < n; i++) {
            String barcode = scanner.nextLine();
            Pattern pattern = Pattern.compile(barcodeRegex);
            Matcher matcher = pattern.matcher(barcode);

            StringBuilder group = new StringBuilder();
            if (matcher.find()) {
                for (char c : barcode.toCharArray()) {
                    if (Character.isDigit(c)) {
                        group.append(c);
                    }
                }
                if (group.length() == 0) {
                    group.append("00");
                }
                System.out.printf("Product group: %s%n", group);
            } else {
                System.out.println("Invalid barcode");
            }
        }
    }
}
