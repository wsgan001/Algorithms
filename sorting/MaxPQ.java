package sorting;

import java.awt.RenderingHints.Key;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/***************************************************
 * 
 * @author Cello
 *
 * 最大堆，使用基于数组的完全二叉树表示
 **************************************************/
public class MaxPQ<Key> implements Iterable<Key> {//

	private Key[] pq; // 1...N存储数据
	private int N; //数据个数
	private Comparator<Key> comparator;
	
	/**
	 * initialize an empty priority queue with the given capacity
	 * @param initCapacity the initial capacity of the priority queue
	 */
	public MaxPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity +1];
		N = 0;
	}
	/**
	 * Initialize an empty priority queue
	 */
	public MaxPQ() {
		this(1);
	}
	/**
	 * Initialize an empty priority queue with the given initial capacity, using the given comparator
	 * @param initCapacity
	 * @param comparator
	 */
	public MaxPQ(int initCapacity, Comparator<Key> comparator) {
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		N = 0;
	}
	/**
	 * Initialize an empty priority queue using the given comparator
	 * @param comparator
	 */
	public MaxPQ(Comparator<Key> comparator) {
		this(1, comparator);
	}
	/**
	 * Initialize a priority queue from the array of keys
	 * @param keys
	 */
	public MaxPQ(Key[] keys) {
		N = keys.length;
		pq = (Key[]) new Object[N + 1];
		for(int i = 0; i < N; i ++) {
			pq[i + 1] = keys[i];
		}
		// adjust the queue to priority queue
		for(int i = N/2; i>0; i--) {
			sink(i);
		}
		
		assert isMaxHeap();
	}
	public boolean isEmpty() {
		return N==0;
	}
	public int size() {
		return N;
	}
	public Key max() {
		if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}
	public void insert(Key x) {
		if( N >= pq.length-1)resize(2*pq.length);
		pq[++N] = x;
		swim(N);
		assert isMaxHeap();
	}
	public Key delMax() {
		if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		Key max = pq[1];
		exch(1, N);
		N--;
		pq[N+1] = null;
		sink(1);
		assert isMaxHeap();
		return max;
	}
	private void resize(int capacity) {
		assert capacity > N;
		Key[] temp = (Key[]) new Object[capacity];
		for(int i = 1; i< N; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}
	private boolean isMaxHeap() {
		return isMaxHeap(1);
	}
	private boolean isMaxHeap(int k) {
		if( k >= N) return true;
		int left = 2*k, right = 2*k +1;
		if(left <= N && less(k, left)) return false;
		if(right <= N && less(k, right)) return false;
		return isMaxHeap(left)&&isMaxHeap(right);
	}
	/**
	 * restore heap
	 * @param k
	 */
	private void sink(int k) {//利用完全二叉树的性质
		while(2*k <= N) {
			int j = 2 *k;
			if(j < N && less(j, j+1)) j ++;
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	/**
	 * restore heap
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1 && less(k, k/2)) {
			exch(k, k/2);
			k /=2;
		}
	}
	private boolean less(int i, int j) {
		if(comparator == null) {
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
		} else {
			return comparator.compare(pq[i], pq[j]) < 0;
		}
	}
	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return new HeapIterator();
	}
	private class HeapIterator implements Iterator<Key> {
		private MaxPQ<Key> copy;
		public HeapIterator() {
			if(comparator == null) copy = new MaxPQ<Key>(size());
			else copy = new MaxPQ<Key>(size(), comparator);
			for(int i=1; i< N; i++) {
				copy.insert(pq[i]);
			}
		}
		@Override
		public boolean hasNext() {
			return !copy.isEmpty();
		}

		@Override
		public Key next() {
			if(!hasNext()) throw new NoSuchElementException(); 
			return copy.delMax();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
	}
}
