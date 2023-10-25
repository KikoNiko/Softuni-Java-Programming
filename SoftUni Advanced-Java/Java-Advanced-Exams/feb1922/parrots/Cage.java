package feb1922.parrots;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Parrot> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
    public void add(Parrot parrot) {
        if (data.size() < capacity) {
            data.add(parrot);
        }
    }

    public boolean remove(String name) {
        for (Parrot p : data) {
            if (p.getName().equals(name)) {
                data.remove(p);
                return true;
            }
        }
        return false;
    }

    public Parrot sellParrot(String name) {
        for (Parrot p : data) {
            if (p.getName().equals(name)) {
                p.setAvailable(false);
                return p;
            }
        }
        return null;
    }

    public List<Parrot> sellParrotBySpecies(String species) {
        Predicate<Parrot> predicate = p -> p.getSpecies().equals(species);
        data.stream().filter(predicate).forEach(p -> p.setAvailable(false));
        return data.stream().filter(predicate).collect(Collectors.toList());
    }

    public int count() {
        return data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Parrots available at %s:%n", this.name));
        for (Parrot parrot : data) {
            if (parrot.isAvailable()) {
                sb.append(parrot.toString()).append('\n');
            }
        }

        return sb.toString();
    }
}
