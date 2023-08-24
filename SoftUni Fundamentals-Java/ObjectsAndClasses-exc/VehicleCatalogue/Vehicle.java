package VehicleCatalogue;

public class Vehicle {

    private String type;
    private String model;
    private String color;
    private int horsepower;

    public String getType() {
        return type;
    }
    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public Vehicle(String type, String model, String color, int horsepower) {
        this.type = type;
        this.model = model;
        this.color = color;
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return String.format("Type: %s%nModel: %s%nColor: %s%nHorsepower: %d",
                this.type.substring(0, 1).toUpperCase() + this.type.substring(1),
                this.model,
                this.color,
                this.horsepower);
    }
}
