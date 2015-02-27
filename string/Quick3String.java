package string;
/****************************************
 * 
 * @author Cello
 * �����зֵ��ַ�����������
 * ��ֵ�����нϳ��Ĺ���ǰ׺�ļ���ȡֵ��Χ��С�ļ���С�����MSD�������������з����кܺõĽ����
 * ���ڷǿ��зֽ���ʱ�������зֵ��ƶ�����Ҫ��MSD�Ķ����зֶ�
 ****************************************/
public class Quick3String {

	private static int charAt(String s, int d) {
		if(d < s.length()) return s.charAt(d);
		else return -1;
	}
	public static void sort(String[] a) {
		sort(a, 0, a.length-1, 0);
	}
	private static void sort(String[] a, int lo, int hi, int d){
		if(hi <= lo) return;
		int lt = lo, gt = hi;
		int i = lt+1;
		int v = charAt(a[lo], d);
		while(lt <= gt) {
			int t = charAt(a[i], d);
			if(t < v) {
				exch(a, i, lt);
				i++;
				lt++;
			} else if(t > v){
				exch(a, i, gt);
				gt --;
			} else {
				i++;
			}
		}
		sort(a, lo, lt-1, d);
		if(v >= 0){
			sort(a, lt, gt, d+1);
		}
		sort(a, gt+1, hi, d);
	}
	private static void exch(String[] a, int i, int j){
		String t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
