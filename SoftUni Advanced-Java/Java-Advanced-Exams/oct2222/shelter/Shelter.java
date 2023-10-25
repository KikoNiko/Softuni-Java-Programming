package oct2222.shelter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shelter {
    private List<Animal> data;
    private int capacity;


    public Shelter(int capacity) {
        this.data = new ArrayList<>();
        this.capacity = capacity;
    }

    public void add(Animal animal) {
        if (this.data.size() < capacity) {
            this.data.add(animal);
        }
    }

    //⦁	Method remove(String name) – removes the animal by given name, if such exists, and returns boolean
    public boolean remove(String name) {
        return this.data.removeIf(a -> a.getName().equals(name));
    }

    //⦁	Method getAnimal(String name, String caretaker) –
    // returns the animal with the given name and caretaker or null if no such animal exists.
    public Animal getAnimal(String name, String caretaker) {
        return this.data.stream()
                .filter(a -> a.getName().equals(name) && a.getCaretaker().equals(caretaker))
                .findFirst().orElse(null);
    }

    public Animal getOldestAnimal() {
        return this.data.stream()
                .max(Comparator.comparingInt(Animal::getAge))
                .orElse(null);
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("The oct2222.shelter has the following animals:");
        for (Animal animal : this.data) {
            sb.append(System.lineSeparator());
            sb.append(animal.getName()).append(" ").append(animal.getCaretaker());
        }
        return sb.toString();
    }
}
