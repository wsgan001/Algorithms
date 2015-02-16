package search;

/**
 * Created by basin on 16/2/15.
 */
public class HashMap {
    private static class Node{
        String key;
        String value;
        Node next;
        public Node(String k, String v) {
            this.key = k;
            this.value = v;
        }
    }
    private Node[] nodes;
    private int prime;
    public HashMap(int capacity, int prime) {
        nodes = new Node[prime + 1];
        this.prime = prime;
    }
    private int hash(String key) {
        long r = 0;
        for (int i = 0; i < key.length(); i++) {
            r = (r*31+(int)key.charAt(i)) % prime;
        }
        return (int)(r % prime);
    }
    public boolean containsKey(String key) {
        if (key == null) return true;
        int index = hash(key);
        Node p = nodes[index];
        while (p != null) {
            if (p.key.equals(key)) return true;
            p = p.next;
        }
        return false;
    }
    public boolean put(String key, String value) {
        int index = hash(key);
        if (nodes[index] == null) {
            nodes[index] = new Node(key, value);
            return true;
        } else {
            Node p = nodes[index];
            while (p.next != null) {
                if (p.key.equals(key)) {
                    p.value = value;
                    return true;
                }
                p = p.next;
            }
            if (p.key.equals(key)) {
                p.value = value;
                return true;
            }
            Node q = new Node(key, value);
            p.next = q;
            return true;
        }
    }
    public String get(String key) {
        int index = hash(key);
        if (nodes[index] != null) {
            Node p = nodes[index];
            while (p != null) {
                if (p.key.equals(key)) {
                    return p.value;
                }
                p = p.next;
            }
        }
        return null;
    }
}
