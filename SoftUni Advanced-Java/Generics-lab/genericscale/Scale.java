package genericscale;

import java.util.Comparator;

public class Scale<T extends Comparable<T>> {

    private final T left;
    private final T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getHeavier() {
        if (left.compareTo(right) > 0) {
            return left;
        } else if (left.compareTo(right) < 0) {
            return right;
        }
        return null;
    }
}
