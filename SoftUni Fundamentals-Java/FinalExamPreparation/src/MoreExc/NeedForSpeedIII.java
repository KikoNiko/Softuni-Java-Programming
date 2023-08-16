package MoreExc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class NeedForSpeedIII {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfCars = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> mileageMap = new LinkedHashMap<>();
        Map<String, Integer> fuelMap = new LinkedHashMap<>();

        for (int i = 0; i < numberOfCars; i++) {
            String[] carsInfo = scanner.nextLine().split("\\|");
            String car = carsInfo[0];
            int mileage = Integer.parseInt(carsInfo[1]);
            int fuel = Integer.parseInt(carsInfo[2]);
            mileageMap.putIfAbsent(car, mileage);
            fuelMap.putIfAbsent(car, fuel);
        }

        String commands = scanner.nextLine();
        while (!commands.equals("Stop")) {
            String[] tokens = commands.split(" : ");
            String command = tokens[0];
            String car = tokens[1];
            switch (command) {
                case "Drive":
                    //"Drive : {car} : {distance} : {fuel}":
                    int distance = Integer.parseInt(tokens[2]);
                    int fuel = Integer.parseInt(tokens[3]);
                    if (fuelMap.get(car) < fuel) {
                        System.out.println("Not enough fuel to make that ride");
                    } else {
                        int newFuel = fuelMap.get(car) - fuel;
                        int newMileage = mileageMap.get(car)  + distance;
                        mileageMap.put(car, newMileage);
                        fuelMap.put(car, newFuel);
                        System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n", car, distance, fuel);
                        if (newMileage >= 100000) {
                            System.out.printf("Time to sell the %s!%n", car);
                            mileageMap.remove(car);
                            fuelMap.remove(car);
                        }
                    }
                    break;
                case "Refuel":
                    //Refuel : {car} : {fuel}
                    int fuelToPut = Integer.parseInt(tokens[2]);
                    int newFuel = fuelMap.get(car) + fuelToPut;
                    if (newFuel > 75) {
                        fuelToPut = 75 - fuelMap.get(car);
                        newFuel = 75;
                    }
                    fuelMap.put(car, newFuel);
                    System.out.printf("%s refueled with %d liters%n", car, fuelToPut);
                    break;
                case "Revert":
                    int kilometers = Integer.parseInt(tokens[2]);
                    int newMileage = mileageMap.get(car) - kilometers;
                    if (newMileage >= 10000) {
                        System.out.printf("%s mileage decreased by %d kilometers%n", car, kilometers);
                    } else {
                        newMileage = 10000;
                    }
                    mileageMap.put(car, newMileage);
                    break;
            }
            commands = scanner.nextLine();
        }
        //{car} -> Mileage: {mileage} kms, Fuel in the tank: {fuel} lt.
        for (Map.Entry<String, Integer> entry : mileageMap.entrySet()) {
            System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n", entry.getKey(), entry.getValue(), fuelMap.get(entry.getKey()));
        }

    }
}
