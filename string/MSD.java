package string;

import sorting.Insertion;

/*************************************
 * 
 * @author Cello
 * Most significant digit
 * �ڸ�λ���ȵ��ַ��������У�Ҫ�ر�ע�⵽���ַ���ĩβ����������������±������ﴦ�����������
 * �ڼ��нϳ��Ĺ����Ӵ�ʱ���ݹ������������Ӵ��ĳ��ȳ�����
 * �ռ�����Ҳ�ϴ󣬶���count[Alphabet.R()+2]��ÿ���ݹ�ջ�������һ������������
 * С��������������ڿ������򡢹鲢������Ҳ�г��֣���˿����������Сʱ�лز�������
 * �������нϳ��Ĺ����Ӵ�ʱ���л�����������������ᱻ����
 * �����ַ�������������ԸĽ�
 ************************************/
public class MSD {
	private static int R = 256;
	private static int M = 15;
	private static String[] aux;
	private static int charAt(String s, int d) {
		if(d < s.length()) return s.charAt(d);
		else return -1;
	}
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N-1, 0);
	}
	private static void sort(String[] a, int lo, int hi, int d) {
		if(lo + M >= hi){
			Insertion.sort(a, lo, hi, d);
			return;
		}
		int[] count = new int[R+2];
		// ͳ���ַ�
		for(int i = lo; i <= hi; i++) {
			count[charAt(a[i], d) + 2] ++;
		}
		// ת��Ϊ����
		for(int i = 0; i < R+1; i++){
			count[i+1] += count[i];
		}
		// ���ݷ���
		for(int i = lo; i<= hi; i++){
			aux[count[charAt(a[i],d)+1]++] = a[i];
		}
		// ��д
		for(int i = lo; i<= hi; i++) {
			a[i] = aux[i-lo];
		}
		//�ݹ����ÿ���ַ�Ϊ����������
		for(int r = 0; r <R; r++){
			sort(a, lo+count[r], lo+count[r+1]-1, d+1);
		}
	}
}
