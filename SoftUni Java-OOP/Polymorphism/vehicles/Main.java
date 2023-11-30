package vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        Vehicle car = new Car(
                Double.parseDouble(tokens[1]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[3]));

        tokens = scanner.nextLine().split("\\s+");
        Vehicle truck = new Truck(
                Double.parseDouble(tokens[1]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[3]));

        tokens = scanner.nextLine().split("\\s+");
        Vehicle bus = new Bus(
                Double.parseDouble(tokens[1]),
                Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[3]));

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);
        vehicleMap.put("Bus", bus);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split("\\s+");
            Vehicle vehicle = vehicleMap.get(tokens[1]);
            double param = Double.parseDouble(tokens[2]);

            try {
                if ("Drive".equals(tokens[0])) {
                    if ("Bus".equals(tokens[1])) {
                        vehicle.turnOnAC();
                        System.out.println(vehicle.drive(param));
                    } else {
                        System.out.println(vehicle.drive(param));
                    }
                } else if ("Refuel".equals(tokens[0])) {
                    vehicle.refuel(param);
                } else {
                    System.out.println(vehicle.drive(param));
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        vehicleMap.values().forEach(System.out::println);
    }
}
