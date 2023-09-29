import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class _01_ParkingLot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Set<String> parkingLot = new LinkedHashSet<>();

        while (!input.equals("END")) {

            String[] tokens = input.split(", ");
            String inout = tokens[0];
            String licensePlate = tokens[1];

            if (inout.equals("IN")) {
                parkingLot.add(licensePlate);
            } else {
                parkingLot.remove(licensePlate);
            }

            input = scanner.nextLine();
        }

        if (parkingLot.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            for (String lp : parkingLot) {
                System.out.println(lp);
            }
        }
    }
}
