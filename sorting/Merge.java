package sorting;
/*****************************************************
 * 
 * @author Cello
 * 归并排序，空间复杂度O(N)，是稳定排序
 * 可能的优化：1，当剩余的子数组比较小的时候，切换为插入排序，效果更好。递归产生很多小的问题，使得调用过程很繁琐
 *,2，不必每次都复制子数组，可以交换使用两个数组
 ****************************************************/
public class Merge {
	private static Comparable[] aux;
	//也可以自底向上，一路合并。注意会有长度不同的子数组合并
	public static void sortBU(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for(int step = 1; step < N; step += step) {
			for(int low = 0; low < N-step; low+=(step<<1)){
				merge(a, low, low+step-1, Math.min(N-1, low+(step<<1)-1));
			}
		}
	}
	//自顶向下，先分再合
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a, int low, int high) {
		if(low >= high) return;
		int mid = low + (high - low)/2;
		sort(a, low, mid);
		sort(a, mid+1, high);
		merge(a, low, mid, high);
	}
	private static void merge(Comparable[] a, int low, int mid, int high) {
		//将a[low]...a[mid]和a[mid+1]...a[high]合并
		int i = low, j = mid+1;
		for(int k = low; k <= high; k++) {//备份a[low---high]
			aux[k] = a[k];
		}
		//觉得这个写法就挺优雅的
		for(int k = low; k <= high; k++) {
			if(i>mid){//a[low-mid]已结束
				a[k] = aux[j++];
			} else if(j > high) {
				a[k] = aux[i++];
			} else if(less(aux[i], aux[j])){
				a[k] = aux[i++];
			} else {
				a[k] = aux[j++];
			}
		}
	}
	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w)<0);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
