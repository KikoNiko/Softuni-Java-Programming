package VehicleCatalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Vehicle> vehicles = new ArrayList<>();

        String input = scanner.nextLine();
        while (!input.equals("End")) {
            String[] tokens = input.split(" ");
            String type = tokens[0];
            String model = tokens[1];
            String color = tokens[2];
            int horsepower = Integer.parseInt(tokens[3]);

            Vehicle vehicle = new Vehicle(type, model, color, horsepower);
            vehicles.add(vehicle);

            input = scanner.nextLine();
        }
        String vehicleModel = scanner.nextLine();
        while (!vehicleModel.equals("Close the Catalogue")) {
            for (Vehicle v : vehicles) {
                if (v.getModel().equals(vehicleModel)) {
                    System.out.println(v);
                }
            }
            vehicleModel = scanner.nextLine();
        }

        double sumCarsHP = 0;
        double sumTrucksHP = 0;
        int countCars = 0;
        int countTrucks = 0;

        for (Vehicle v : vehicles) {
            if (v.getType().equals("car")) {
                sumCarsHP += v.getHorsepower();
                countCars++;
            } else {
                sumTrucksHP += v.getHorsepower();
                countTrucks++;
            }
        }

        double avgCars = sumCarsHP / countCars;
        double avgTrucks = sumTrucksHP / countTrucks;
        if (countCars == 0) { avgCars = 0;}
        if (countTrucks == 0) { avgTrucks = 0;}
        System.out.printf("Cars have average horsepower of: %.2f.%n", avgCars);
        System.out.printf("Trucks have average horsepower of: %.2f.%n", avgTrucks);

    }
}
