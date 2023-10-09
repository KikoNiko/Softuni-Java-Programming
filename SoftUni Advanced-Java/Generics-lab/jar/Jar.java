package jar;

import java.util.ArrayDeque;

public class Jar<T> {

    private ArrayDeque<T> stack;

    public Jar() {
        this.stack = new ArrayDeque<T>();
    }

    public void add(T e) {
        stack.push(e);
    }

    public T remove() {
        return stack.pop();
    }
}
