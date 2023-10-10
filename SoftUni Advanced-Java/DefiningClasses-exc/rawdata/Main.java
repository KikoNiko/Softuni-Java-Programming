package rawdata;

import java.util.*;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Set<Car> carSet = new LinkedHashSet<>();

        for (int i = 0; i < n; i++) {
            String[] carData = scanner.nextLine().split("\\s+");
            String carModel = carData[0];
            int engineSpeed = Integer.parseInt(carData[1]);
            int enginePower = Integer.parseInt(carData[2]);
            Engine engine = new Engine(engineSpeed, enginePower);
            int cargoWeight = Integer.parseInt(carData[3]);
            String cargoType = carData[4];
            Cargo cargo = new Cargo(cargoWeight, cargoType);
            Car car = new Car(carModel, engine, cargo, new ArrayList<>());
            for (int j = 5; j < 12; j+=2) {
                double tirePressure = Double.parseDouble(carData[j]);
                int tireAge = Integer.parseInt(carData[j+1]);
                Tire tire = new Tire(tirePressure, tireAge);
                car.getTires().add(tire);
            }
            carSet.add(car);
        }

        Predicate<Car> isFragile = c -> c.getCargo().getType().equals("fragile");
        Predicate<Car> isLowPressure = c -> c.getTires().stream().anyMatch(t -> t.getPressure() < 1);
        Predicate<Car> isFlamable = c -> c.getCargo().getType().equals("flamable");

        String command = scanner.nextLine();
        if (command.equals("fragile")) {
            carSet.stream()
                    .filter(isFragile)
                    .filter(isLowPressure)
                    .forEach(System.out::println);
        } else if (command.equals("flamable")) {
            carSet.stream()
                    .filter(isFlamable)
                    .filter(c -> c.getEngine().getPower() > 250)
                    .forEach(System.out::println);
        }
    }

}
