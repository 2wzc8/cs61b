package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = items[(head + i) % items.length];
        }
        items = a;
        head = 0;
        tail = size;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        head = (head - 1 + items.length) % items.length;
        items[head] = x;
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[tail] = x;
        tail = (tail + 1) % items.length;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                System.out.print(items[(head + i) % items.length] + " ");
            } else {
                System.out.println(items[(head + i) % items.length]);
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T k = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size -= 1;
        if (size > 0 && size == items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        return k;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        tail = (tail - 1 + items.length) % items.length;
        T k = items[tail];
        items[tail] = null;
        size -= 1;
        if (size > 0 && size == items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        return k;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index) % items.length];
    }

    private class DequeIterator implements Iterator<T> {
        private int pos = 0;

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            T item = items[(head + pos) % items.length];
            pos++;
            return item;
        }
    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque<?>)) {
            return false;
        }
        Deque<?> other = (Deque<?>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            T a = this.get(i);
            Object b = other.get(i);
            if (a == null) {
                if (b != null) {
                    return false;
                }
            } else if (!a.equals(b)) {
                return false;
            }
        }
        return true;
    }
}
