package customlist;

import java.util.*;
import java.util.function.Consumer;

public class CustomList<T extends Comparable<T>> implements Iterable<T> {

    private final List<T> data;

    public CustomList() {
        this.data = new ArrayList<>();
    }

    public T get(int i) {
        return this.data.get(i);
    }

    public void add(T element) {
        this.data.add(element);
    }

    public T remove(int index) {
        return this.data.remove(index);
    }

    public boolean contains(T element) {
        return this.data.contains(element);
    }

    public void swap(int index1, int index2) {
        T el1 = this.data.get(index1);
        T el2 = this.data.get(index2);

        this.data.set(index1, el2);
        this.data.set(index2, el1);
    }

    public int countGreaterThan(T element) {
        int count = 0;
        for (T e : this.data) {
            int res = e.compareTo(element);
            if (res > 0) {
                count++;
            }
        }
        return count;
    }

    public T getMax() {
        return Collections.max(this.data);
    }

    public T getMin() {
        return Collections.min(this.data);
    }

    public int getSize() {
        return this.data.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return this.index < data.size();
            }

            @Override
            public T next() {
                return data.get(index++);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        this.data.forEach(action);
    }
}
