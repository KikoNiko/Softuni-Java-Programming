package feb2021.dealership;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dealership {
    private List<Car> data;
    private String name;
    private int capacity;

    public Dealership(String name, int capacity) {
        this.data = new ArrayList<>();
        this.name = name;
        this.capacity = capacity;
    }

    //⦁	Method add(Car car) – adds an entity to the data if there is an empty cell for the car.
    public void add(Car car) {
        if (this.data.size() < this.capacity) {
            this.data.add(car);
        }
    }
    //⦁	Method buy(String manufacturer, String model) –
    // removes the car by given manufacturer and model, if such exists, and returns boolean.
    public boolean buy(String manufacturer, String model) {
        return this.data.removeIf(c -> c.getManufacturer().equals(manufacturer)
                && c.getModel().equals(model));
    }

    //⦁	Method getLatestCar() – returns the latest car (by year) or null if have no cars.
    public Car getLatestCar() {
        return this.data.stream()
                .max(Comparator.comparing(Car::getYear))
                .orElse(null);
    }
    //⦁	Method getCar(String manufacturer, String model) –
    // returns the car with the given manufacturer and model or null if there is no such car.
    public Car getCar(String manufacturer, String model) {
        return this.data.stream()
                .filter(c -> c.getManufacturer().equals(manufacturer)
                        && c.getModel().equals(model))
                .findFirst()
                .orElse(null);
    }
    //⦁	Getter getCount() – returns the number of cars.
    public int getCount() {
        return this.data.size();
    }

    //⦁	" The cars are in a car feb2021.dealership {name}:
    //{Car1}
    //{Car2}
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("The cars are in a car feb2021.dealership %s:", this.name));
        for (Car car : this.data) {
            sb.append(System.lineSeparator());
            sb.append(car.toString());
        }
        return sb.toString();
    }
}
