package vehicles;

public class Bus extends AbstractVehicle {

    private static final double AC_ON_FACTOR = 1.4;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public void turnOnAC() {
        setEmpty(false);
        this.setFuelConsumption(this.getFuelConsumption() + AC_ON_FACTOR);
    }
}
