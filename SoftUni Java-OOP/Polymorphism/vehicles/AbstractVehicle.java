package vehicles;

import java.text.DecimalFormat;

public abstract class AbstractVehicle implements Vehicle {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    private boolean isEmpty;

    public AbstractVehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        setFuelQuantity(fuelQuantity);
        setFuelConsumption(fuelConsumption);
        setTankCapacity(tankCapacity);
        this.isEmpty = true;
    }

    public void setFuelQuantity(double fuelQuantity) {
        if (fuelQuantity <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }


    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String drive(double distance) {
        double fuelNeeded = this.fuelConsumption * distance;
        if (this.fuelQuantity >= fuelNeeded) {
            this.fuelQuantity -= fuelNeeded;
            return String.format("%s travelled %s km",
                    this.getClass().getSimpleName(),
                    DECIMAL_FORMAT.format(distance));
        }
        return String.format("%s needs refueling", this.getClass().getSimpleName());
    }


    @Override
    public void refuel(double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if (this.fuelQuantity + liters > this.tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
