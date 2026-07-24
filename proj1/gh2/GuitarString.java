package gh2;

import deque.Deque;
import deque.LinkedListDeque;

public class GuitarString {
    private static final int SR = 44100;
    private static final double DECAY = .996;

    private Deque<Double> buffer;

    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new LinkedListDeque<Double>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }

    public void pluck() {
        for (int i = 0; i < buffer.size(); i++) {
            double r = Math.random() - 0.5;
            buffer.addFirst(r);
            buffer.removeLast();
        }
    }

    public void tic() {
        double t1 = buffer.get(0);
        double t2 = buffer.get(1);
        buffer.removeFirst();
        double t3 = (t1 + t2) / 2;
        double ans = t3 * DECAY;
        buffer.addLast(ans);
    }

    public double sample() {
        if (buffer.isEmpty()) {
            return 0;
        } else {
            return buffer.get(0);
        }
    }
}
