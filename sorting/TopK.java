package sorting;

import java.util.ArrayList;
import java.util.List;

/*******************************************
 * 
 * @author Cello
 * 寻找Top k数据
 * 1， 使用选择排序的partition
 * 2. 维护一个大小为k的最大/最小堆，适合海量流式文件处理
 ******************************************/
public class TopK {

	private static List<Comparable> getTopK(int k, Comparable[] a) {
		List<Comparable> topK = new ArrayList<Comparable>();
		if(a == null || k <= 0 || k > a.length) return topK;
		int i = 0, j = a.length - 1;
		while(i <= j) {
			int m = partition(a, i, j);
			if(m == k-1) {
				for(int p = 0; p < k; p ++) {
					topK.add(a[p]);
				}
				return topK;
			}else if( m > k - 1) {
				j = m;
			} else {
				i = m;
			}
		}
		return topK;
	}
	private static int partition(Comparable[] a, int left, int right) {
		if(left == right) return left;
		int pivot = pivotSelect(a, left, right);
		exch(a, pivot, right);
		int i = left, j = right - 1;
		while(i < j) {
			while(i < j && less(a, i, right)) i++;
			while(i < j && less(a, right, j)) j--;
			exch(a, i, j);
		}
		exch(a, i+1, right);
		return i+1;
	}
	private static int pivotSelect(Comparable[] a, int left, int right) {
		return left + (right - left)/2;
	}
	private static boolean less(Comparable[] a, int i, int j) {
		return a[i].compareTo(a[j]) < 0;
	}
	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
