package sorting;
/**************************************
 * 
 * @author Cello
 * ������
 */
public class HeapSort {
	// ���������±��1��ʼ�����ñ�ʾһЩ
	public static void sort(Comparable[] a) {
		int N = a.length;
		for(int k = (N-1)/2; k >= 0; k --) {
			sink(a, k, N);
		}
		while(N > 1) {
			exch(a, 0, N-1);
			N--;
			sink(a, 0, N);
		}
	}
	private static void sink(Comparable[] a, int k, int N) {
		while(2 * k + 1 < N) {
			int j = 2*k+1;
			if(j < N-1 && less(a, j+1, j)) j++;
			if(less(a, k, j)) break;
			exch(a, k, j);
			k = j;
		}
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
