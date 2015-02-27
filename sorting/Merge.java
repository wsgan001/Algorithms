package sorting;
/*****************************************************
 * 
 * @author Cello
 * �鲢���򣬿ռ临�Ӷ�O(N)�����ȶ�����
 * ���ܵ��Ż���1����ʣ���������Ƚ�С��ʱ���л�Ϊ��������Ч�����á��ݹ�����ܶ�С�����⣬ʹ�õ��ù��̺ܷ���
 *,2������ÿ�ζ����������飬���Խ���ʹ����������
 ****************************************************/
public class Merge {
	private static Comparable[] aux;
	//Ҳ�����Ե����ϣ�һ·�ϲ���ע����г��Ȳ�ͬ��������ϲ�
	public static void sortBU(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for(int step = 1; step < N; step += step) {
			for(int low = 0; low < N-step; low+=(step<<1)){
				merge(a, low, low+step-1, Math.min(N-1, low+(step<<1)-1));
			}
		}
	}
	//�Զ����£��ȷ��ٺ�
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
		//��a[low]...a[mid]��a[mid+1]...a[high]�ϲ�
		int i = low, j = mid+1;
		for(int k = low; k <= high; k++) {//����a[low---high]
			aux[k] = a[k];
		}
		//�������д����ͦ���ŵ�
		for(int k = low; k <= high; k++) {
			if(i>mid){//a[low-mid]�ѽ���
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
