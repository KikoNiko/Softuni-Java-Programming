package dec1521.aquarium;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {

    private List<Fish> fishInPool;
    private String name;
    private int capacity;
    private int size;

    public Aquarium(String name, int capacity, int size) {
        this.fishInPool = new ArrayList<>();
        this.name = name;
        this.capacity = capacity;
        this.size = size;
    }

    public int getFishInPool() {
        return fishInPool.size();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    //Method add(Fish fish) - add the entity if there isn't a fish with the same name and if there is enough space for it.
    public void add(Fish fish) {
        if (!fishInPool.contains(fish) && this.fishInPool.size() < this.capacity) {
            fishInPool.add(fish);
        }
    }

    //⦁	Method remove(String name) -
    // removes a fish from the pool with the given name,
    // if such exists, and returns a boolean if the deletion is successful.
    public boolean remove(String name) {
        for (Fish fish : fishInPool) {
            if (fish.getName().equals(name)) {
                fishInPool.remove(fish);
                return true;
            }
        }
        return false;
    }

    //⦁	Method findFish(String name) - returns a fish with the given name, it doesn't exist return null.
    public Fish findFish(String name) {
        for (Fish fish : fishInPool) {
            if (name.equals(fish.getName())) {
                return fish;
            }
        }
        return null;
    }

    //Aquarium: {name} ^ Size: {size}
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Aquarium: %s ^ Size: %d", this.name, this.size));
        for (Fish fish : fishInPool) {
            sb.append(System.lineSeparator());
            sb.append(fish.toString());
        }
        return sb.toString();
    }
}
