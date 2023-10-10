package rawdata;

import java.util.List;
import java.util.Map;

public class Car {
    private String model;

    private Engine engine;
    private Cargo cargo;
    private List<Tire> tires;

    public List<Tire> getTires() {
        return tires;
    }

    public Engine getEngine() {
        return engine;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Car(String model, Engine engine, Cargo cargo, List<Tire> tires) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tires = tires;
    }

    @Override
    public String toString() {
        return this.model;
    }
}
