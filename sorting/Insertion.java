package sorting;
/****************************************
 * 
 * @author Cello
 * �������� ���Ǳ�֤ǰ���i�����򣬶Ե�i+1��Ԫ�أ�������ǰ���Ԫ�رȽϣ�С���򽻻�
 * �����������ȶ���
 * �������С���ֲ�����ʱ��������������ܽϺ�
 * �Ż�1�������ڱ������ź���С��Ԫ�أ��Ͳ����ж�j>0����Ϊless����һ���ᱻ����
 * �Ż�2��ͨ�����ϴ��Ԫ������ƶ�һλ�����������ݷ��ʴ�������Ϊ�������������ݷ��ʣ�
 * Shell ����h���������һ���С����h������־ֲ������ˣ����ǲ��������ó�������
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
