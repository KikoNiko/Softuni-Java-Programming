package genericbox;

public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {

    private final T value;

    public Box(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.getClass().getName() + ": " + this.value;
    }

    @Override
    public int compareTo(Box<T> o) {
        return this.value.compareTo(o.value);
    }
}
