
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * **************************************************
 * Created by basin on 15-2-1.
 * **************************************************
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N = 0;
    private Item[] items;
    public RandomizedQueue() {
        items = (Item[])new Object[1];
    }
    public boolean isEmpty() {
        return this.N == 0;
    }
    public int size() {
        return this.N;
    }
    public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();
        if (this.N == items.length) {
            resize(items.length << 1);
        }
        items[this.N++] = item;
    }
    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i< N; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniform(this.N);
        // exchange i and N-1
        Item item = items[i];
        items[i] = items[N-1];
        this.N--;
        return item;
    }
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniform(this.N);
        return items[i];
    }
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copy;
        private int i = 0;
        public RandomizedQueueIterator() {
            copy = (Item[]) new Object[N];
            for (int i = 0; i< N; i ++) {
                copy[i] = items[i];
            }
            StdRandom.shuffle(copy);
        }
        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        for (String s: queue) {
            StdOut.print(s+"\t");
        }
        StdOut.println();
        StdOut.println("Sample: " + queue.sample());
        StdOut.println("dequeue: " + queue.dequeue());
        for (String s: queue) {
            StdOut.print(s+"\t");
        }
        StdOut.println();
        StdOut.println("empty: " + queue.isEmpty());
        StdOut.println("size: " + queue.size());
    }
}
