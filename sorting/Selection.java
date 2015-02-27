package sorting;
/**********************************
 * 
 * @author Cello
 * 选择排序，每次都选择剩余的最小的元素，选择排序的数据移动是最少的，比较次数是二次方级别
 *********************************/
public class Selection {

	public static void sort(Comparable[] a) {
		if(a == null || a.length <= 1) return;
		int N = a.length;
		for(int i = 0; i< N; i++) {
			//将a[i]与a[i+1]...a[N-1]的最小值交换
			int min = i;
			for(int j = i+1; j< N;j++) {
				if(less(a[j], a[min])) {
					min = j;
				}
			}
			exch(a, i, min);
		}
	}
	private static boolean less(Comparable a, Comparable b) {
		return (a.compareTo(b)<0);
	}
	private static void exch(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
