package oct2321.hotel;

import java.util.LinkedHashSet;
import java.util.Set;

public class Hotel {

    private Set<Person> roster;
    private String name;
    private int capacity;

    public Hotel(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new LinkedHashSet<>(capacity);
    }

    public void add(Person person) {
        if (roster.size() < capacity) {
            roster.add(person);
        }
    }

    public boolean remove(String name) {
        for (Person person : roster) {
            if (person.getName().equals(name)) {
                roster.remove(person);
                return true;
            }
        }
        return false;
    }

    public Person getPerson(String name, String hometown) {
        for (Person person : roster) {
            if (person.getName().equals(name) && person.getHometown().equals(hometown)) {
                return person;
            }
        }
        return null;
    }

    public int getCount() {
        return roster.size();
    }

    public String getStatistics()  {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("The people in the oct2321.hotel %s are:%n", this.name));
        roster.forEach(p -> sb.append(p).append('\n'));
        return sb.toString().trim();
    }
}
