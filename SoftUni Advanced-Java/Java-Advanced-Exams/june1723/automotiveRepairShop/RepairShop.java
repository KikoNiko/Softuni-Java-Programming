package june1723.automotiveRepairShop;

import java.util.ArrayList;
import java.util.List;

public class RepairShop {

    private int capacity;
    private List<Vehicle> vehicles;


    public RepairShop(int capacity) {
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicles.size() < capacity) {
            vehicles.add(vehicle);
        }
    }

    public boolean removeVehicle(String vin) {
        for (Vehicle v : vehicles) {
            if (vin.equals(v.getVIN())) {
                vehicles.remove(v);
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        return vehicles.size();
    }

    public Vehicle getLowestMileage() {
        Vehicle lowest = vehicles.get(0);
        for (int i = 1; i < vehicles.size(); i++) {
            Vehicle currentVehicle = vehicles.get(i);
            if (currentVehicle.getMileage() < lowest.getMileage()) {
                lowest = currentVehicle;
            }
        }
        return lowest;
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicles in the preparatory:\n");
        vehicles.forEach(sb::append);
        return sb.toString();
    }
}
