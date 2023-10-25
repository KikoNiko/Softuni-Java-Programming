package feb1823.kindergarten;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Kindergarten {

    private String name;
    private int capacity;
    private List<Child> registry;


    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.registry = new ArrayList<>(capacity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean addChild(Child child) {
        if (registry.size() < capacity) {
            registry.add(child);
            return true;
        }
        return false;
    }

    public boolean removeChild(String firstname) {
        for (Child child : registry) {
            if (firstname.equals(child.getFirstName())) {
                registry.remove(child);
                return true;
            }
        }
        return false;
    }

    public int getChildrenCount() {
        return registry.size();
    }

    public Child getChild(String firstName) {
        return registry.stream().filter(c -> c.getFirstName().equals(firstName)).findFirst().orElse(null);
    }

    public String registryReport() {
        Comparator<Child> childComparator =
                Comparator.comparing(Child::getAge)
                        .thenComparing(Child::getFirstName)
                        .thenComparing(Child::getLastName);

        List<Child> sortedChildren = this.registry.stream()
                .sorted(childComparator).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        String firstLine = String.format("Registered children in %s:%n", this.name);
        sb.append(firstLine);

        sortedChildren.forEach(c -> sb.append(c.toString()).append('\n'));

        return sb.toString().trim();
    }

}
