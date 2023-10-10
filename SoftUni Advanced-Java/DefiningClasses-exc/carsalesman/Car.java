package carsalesman;

public class Car {

    private String model;
    private Engine engine;
    private int weight;
    private String color;

    public Car(String model, Engine engine, int weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString() {
        String weight = "n/a";
        if (this.weight != 0) {
            weight = String.valueOf(this.weight);
        }
        String color = "n/a";
        if (this.color != null) {
            color = this.color;
        }
        return String.format("%s: %n%s %nWeight: %s%nColor: %s", model, engine.toString(), weight, color);
    }
    //"{CarModel}:
    // {EngineModel}:
    // Power: {EnginePower}
    // Displacement: {EngineDisplacement}
    // Efficiency: {EngineEfficiency}
    // Weight: {CarWeight}
    // Color: {CarColor}"
}
