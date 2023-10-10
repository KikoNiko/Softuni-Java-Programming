package carsalesman;

public class Engine {

    private String model;
    private int power;
    private int displacement;
    private String efficiency;

    public String getModel() {
        return model;
    }

    public Engine(String model, int power, int displacement, String efficiency) {
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        String displacement = "n/a";
        if (this.displacement != 0) {
            displacement = String.valueOf(this.displacement);
        }
        String efficiency = "n/a";
        if (this.efficiency != null) {
            efficiency = this.efficiency;
        }

        return String.format("%s: %nPower: %d%nDisplacement: %s%nEfficiency: %s", model, power, displacement, efficiency);
    }

}
