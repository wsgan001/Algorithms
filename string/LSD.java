package string;
/*******************
 * 
 * @author Cello
 * Least Significant Digit First
 * Efficient for the key is in narrow range and the length is same for strings
 ********************/
public class LSD {

	public static void sort(String[] a, int W) {
		int N = a.length;
		String[] aux = new String[N];
		int R = 256;
		for(int d = W-1; d>=0; d--) {
			int[] count = new int[R+1];
			// statistic the times that appeared
			for(int i = 0; i< N; i++){
				count[a[i].charAt(d)+1] ++;
			}
			// transform the times to index
			for(int i = 0; i< R; i++){
				count[i+1] +=count[i];
			}
			// place the items to in-place
			for(int i = 0; i<N; i++){
				aux[count[a[i].charAt(d)]++] = a[i];
			}
			// place the items to in-place
			for(int i = 0; i<N; i++){
				a[i] = aux[i];
			}
		}
	}
}
