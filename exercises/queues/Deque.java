
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * **************************************************
 * Created by basin on 15-2-1.
 * **************************************************
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node pre;
    }
    private Node first;
    private Node last;
    private int N = 0;
    public Deque() { }// construct an empty deque
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return N;
    }
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node node = new Node();
        node.item = item;
        node.next = first;
        if (first == null) {
            first = node;
            last = node;
        } else {
            first.pre = node;
            first = node;
        }
        this.N ++;
    }
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node node = new Node();
        node.item = item;
        if (last == null) {
            first = node;
            last = node;
        } else {
            node.pre = last;
            last.next = node;
            last = node;
        }
        this.N ++;
    }
    public Item removeFirst() {
        if (this.isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if (first == last) {
            last = null;
            first = null;
        } else {
            Node node = first.next;
            first.next = null;
            node.pre = null;
            first = node;
        }
        this.N -- ;
        return item;
    }
    public Item removeLast() {
        if (this.isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        if(first == last) {
            last = null;
            first = null;
        } else {
            Node node = last.pre;
            node.next = null;
            last.pre = null;
            last = node;
        }
        this.N --;
        return item;
    }
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item> {
        private Node cur = first;
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("1");
        deque.addFirst("0");
        for (String s: deque) {
            StdOut.print(s+"\t");
        }
        StdOut.println();
        StdOut.println(deque.size());
        StdOut.println(deque.removeFirst());
        deque.addLast("2");
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        deque.addLast("last");
        StdOut.println(deque.size());
        StdOut.println(deque.removeFirst());
    }
}
