package sorting;
/**********************************
 * 
 * @author Cello
 * ѡ������ÿ�ζ�ѡ��ʣ�����С��Ԫ�أ�ѡ������������ƶ������ٵģ��Ƚϴ����Ƕ��η�����
 *********************************/
public class Selection {

	public static void sort(Comparable[] a) {
		if(a == null || a.length <= 1) return;
		int N = a.length;
		for(int i = 0; i< N; i++) {
			//��a[i]��a[i+1]...a[N-1]����Сֵ����
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
