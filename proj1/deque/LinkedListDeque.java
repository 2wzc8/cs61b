package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class ItemNode {
        private T item;
        private ItemNode next;
        private ItemNode prev;
        ItemNode(T i, ItemNode n, ItemNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }
    private ItemNode fronsent;
    private ItemNode backsent;
    private int size;
    public LinkedListDeque() {
        fronsent = new ItemNode(null, null, null);
        backsent = new ItemNode(null, fronsent, fronsent);
        fronsent.prev = backsent;
        fronsent.next = backsent;
        size = 0;
    }
    public void addLast(T x) {
        backsent.prev.next = new ItemNode(x, backsent, backsent.prev);
        backsent.prev = backsent.prev.next;
        size += 1;
    }
    public void addFirst(T x) {
        fronsent.next.prev = new ItemNode(x, fronsent.next, fronsent);
        fronsent.next = fronsent.next.prev;
        size += 1;
    }
    public T removeLast() {
        if (backsent.prev != fronsent && size != 0) {
            T k = backsent.prev.item;
            backsent.prev.prev.next = backsent;
            backsent.prev = backsent.prev.prev;
            if (size == 1) {
                fronsent.next = backsent;
            }
            size -= 1;
            return k;
        }
        return null;
    }
    public T removeFirst() {
        if (backsent.prev != fronsent && size != 0) {
            T k = fronsent.next.item;
            fronsent.next = fronsent.next.next;
            fronsent.next.prev = fronsent;
            if (size == 1) {
                backsent.prev = fronsent;
            }
            size -= 1;
            return k;
        }
        return null;
    }
    public int size() {
        return size;
    }
    public T get(int i) {
        if (i < 0 || i > size - 1) {
            return null;
        }
        ItemNode p = fronsent.next;
        while (i > 0) {
            p = p.next;
            i -= 1;
        }
        return p.item;
    }
    public T getRecursive(int i) {
        if (i < 0 || i > size - 1) {
            return null;
        }
        return getRecursiveHelper(fronsent.next, i);
    }
    private T getRecursiveHelper(ItemNode node, int i) {
        if (i == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, i - 1);
    }
    public void printDeque() {
        ItemNode p = fronsent.next;
        while (p != backsent) {
            if (p != backsent.prev) {
                System.out.print(p.item + " ");
                p = p.next;
            } else {
                System.out.println(p.item);
                p = p.next;
            }
        }
    }
    private class DequeIterator implements Iterator<T> {
        private ItemNode current = fronsent.next;
        public boolean hasNext() {
            return current != backsent;
        }
        public T next() {
            T item = current.item;
            current = current.next;
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
        if (this.size() != other.size()) {
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
