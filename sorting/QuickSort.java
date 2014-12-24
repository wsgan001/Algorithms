package sorting;
/*******************************************
 * 
 * @author Cello
 * 快速排序
 * 优化：1.关于切分元素的选择
 * 2. 可以先shuffle一下原数组
 * 3. 数组变小以后可以切换到插入排序
 * 4. 在数组中有较多的重复元素时，可以使用三项切分，即每次切分为三个部分left-lt,lt+1-gt,gt-right,分别对应<a[pivot],=a[pivot],>a[pivot]
 *******************************************/
public class QuickSort {

	public static void threeWaySort(Comparable[] a) {
		if(a == null || a.length <=1) return;
		threeWaySort(a, 0, a.length - 1);
	}
	private static void threeWaySort(Comparable[] a, int left, int right) {
		if(left >= right) return;
		int lt = left, gt = right-1, i = left;
		int pivot = left + (right - left)/2;
		Comparable flagComparable = a[pivot];
		exch(a, pivot, right);
		while(i< gt) {
			if(less(a[i], flagComparable)) {
				exch(a, i, lt);
				i++;
				lt++;
			} else if(less(flagComparable, a[i])){
				exch(a, i, gt);
				gt--;
			} else {
				i++;
			}
		}
		exch(a, i, right);
		threeWaySort(a, left, lt-1);
		threeWaySort(a, gt+1, right);
	}
	public static void sort(Comparable[] a) {
		if(a == null || a.length <=1) return;
		sort(a, 0, a.length - 1);
	}
	private static void sort(Comparable[] a, int left, int right) {
		if(left == right) return;
		int pivot = partition(a, left, right);
		sort(a, left, pivot-1);
		sort(a, pivot+1, right);
	}
	private static int partition(Comparable[] a, int left, int right) {
		int pivot = left + (right - left)/2;
		Comparable flagComparable = a[pivot];
		exch(a, pivot, right);
		int i = left, j = right - 1;
		while(i<j) {
			// 学会这种写法和思路
			while(i<j && less(a[i], flagComparable)) i++;
			while(i<j && less(flagComparable, a[j])) j--;
			exch(a, i, j);
		}
		exch(a, right, i);
		return i;
	}
	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w)<0);
	}
	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
