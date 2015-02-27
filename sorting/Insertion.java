package sorting;
/****************************************
 * 
 * @author Cello
 * 插入排序， 总是保证前面的i个有序，对第i+1个元素，依次与前面的元素比较，小于则交换
 * 插入排序是稳定的
 * 当数组较小，局部有序时，插入排序的性能较好
 * 优化1：设置哨兵，即放好最小的元素，就不必判断j>0，因为less条件一定会被打破
 * 优化2：通过将较大的元素向后移动一位，来减少数据访问次数（因为交换有两次数据访问）
 * Shell 排序，h间隔的数组一般较小，而h有序后，又局部有序了，都是插入排序擅长的特征
 ***************************************/
public class Insertion {

	public static void shellSort(Comparable[] a) {
		if(a == null || a.length <= 1) return;
		int N = a.length;
		int h = 1;
		while(h<N/3) h = 3*h+1;
		while(h >= 1){
			for(int i = h; i< N; i++) {
				for(int j = i; j>=h && less(a[j], a[j-h]); j-=h) {
					exch(a, j, j-h);
				}
			}
			h /= 3;
		}
	}
	public static void sortX(Comparable[] a) {
		if(a == null || a.length <= 1) return;
		int min = 0;
		int N = a.length;
		for(int i = 1; i< N ; i++) {
			if(less(a[i], a[min])) {
				min = i;
			}
		}
		exch(a, 0, min);
		for(int i = 1; i<N; i++) {
			int j = i-1;
			Comparable v = a[i];
			while(less(v, a[j])){
				a[j+1] = a[j];
				j--;
			}
			a[j+1]=v;
		}
	}
	public static void sort(Comparable[] a) {
		if(a == null || a.length <= 1) return;
		int N = a.length;
		for(int i = 1; i < N; i++) {
			for(int j = i; j >0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
	}
	public static void sort(String[] a, int lo, int hi, int d) {
		if(a == null || a.length <= 1 || (lo+1) >= hi) return;
		for(int i = lo+1; i<= hi; i++) {
			for(int j = i; j> 0 && less(a[j].charAt(d), a[j-1].charAt(d)); j--){
				exch(a, j, j-1);
			}
		}
	}
	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w)<0);
	}
	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
