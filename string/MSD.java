package string;

import sorting.Insertion;

/*************************************
 * 
 * @author Cello
 * Most significant digit
 * 在高位优先的字符串排序中，要特别注意到达字符串末尾的情况，代码里用下标巧妙里处理了这个问题
 * 在键有较长的公共子串时，递归深度与键公共子串的长度成正比
 * 空间消耗也较大，对于count[Alphabet.R()+2]，每个递归栈都会产生一个这样的数组
 * 小型子数组的问题在快速排序、归并排序中也有出现，因此可以在数组较小时切回插入排序
 * 但当键有较长的公共子串时，切换插入排序的条件不会被触发
 * 三向字符串快速排序可以改进
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
		// 统计字符
		for(int i = lo; i <= hi; i++) {
			count[charAt(a[i], d) + 2] ++;
		}
		// 转化为索引
		for(int i = 0; i < R+1; i++){
			count[i+1] += count[i];
		}
		// 数据分类
		for(int i = lo; i<= hi; i++){
			aux[count[charAt(a[i],d)+1]++] = a[i];
		}
		// 回写
		for(int i = lo; i<= hi; i++) {
			a[i] = aux[i-lo];
		}
		//递归地以每个字符为键进行排序
		for(int r = 0; r <R; r++){
			sort(a, lo+count[r], lo+count[r+1]-1, d+1);
		}
	}
}
