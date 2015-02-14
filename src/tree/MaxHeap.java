package tree;

import java.util.NoSuchElementException;

/**
 * Created by basin on 14/2/15.
 */
// Max heap with array
public class MaxHeap {
    private int[] data;
    private int N = 0;
    public MaxHeap() {
        this.data = new int[1];
    }
    public MaxHeap(int capacity) {
        this.data = new int[capacity+1];
    }
    public void add(int element) {
        if (this.N+1 == data.length) {
            resize((data.length << 1)+1);
        }
        data[this.N+1] = element;
        this.N ++;
        swim(this.N);
    }
    private void swim(int i) {
        while (i > 1) {
            i = (i >>> 1);
            int j = 2*i;
            if (j+1 <= this.N && data[j] < data[j+1]) j++;
            if (data[j] > data[i]) {
                swap(i,j);
            } else break;
        }
    }
    private void swap(int i, int j) {
        int t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
    private void resize(int capacity) {
        int[] tData = new int[capacity];
        for (int i = 0; i < data.length; i ++) {
            tData[i] = data[i];
        }
        data = tData;
    }
    public int deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        int max = data[1];
        swap(1, this.N);
        this.N -- ;
        sink(1);
        return max;
    }
    private void sink(int i) {
        while (2*i <= this.N) {
            int j = 2*i;
            if (j+1<=this.N && data[j] < data[j+1]) j++;
            if (data[j] > data[i]) {
                swap(i,j);
                i = j;
            } else break;
        }
    }
    public boolean isEmpty() {
        return this.N == 0;
    }
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);
        maxHeap.add(-1);
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
//        System.out.println(maxHeap.deleteMax());
    }
}
