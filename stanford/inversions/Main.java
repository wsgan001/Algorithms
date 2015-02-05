package stanford.inversions;


import java.io.*;

/**
 * **************************************************
 * Created by basin on 15-2-5.
 * **************************************************
 */
public class Main {
    private static long inversionCount(int[] a, int l, int r) {
        if (l >= r) return 0;
        int mid = l + (r - l) / 2;
        long inverL = inversionCount(a, l, mid);
        long inverR = inversionCount(a, mid+1, r);
        int[] aux = new int[r-l+1];
        for (int i = 0; i< r-l+1; i++) {
            aux[i] = a[i+l];
        }
        long inverM = 0;
        int  i = l, j = mid+1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                a[k] = aux[j-l];
                j ++;
            } else if (j > r) {
                a[k] = aux[i-l];
                i ++;
            } else if (aux[i-l] > aux[j-l]) {
                a[k] = aux[j-l];
                j++;
                inverM += (mid-i+1);
            } else {
                a[k] = aux[i-l];
                i++;
            }
        }
        return inverL + inverM +inverR;
    }
    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i-1] > a[i]) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File
                ("/home/basin/algs4/stanford/IntegerArray.txt")));
        String line = reader.readLine();
        int[] a = new int[100000];
        int i = 0;
        while (line != null) {
            a[i++] = Integer.parseInt(line);
            line = reader.readLine();
        }
        reader.close();
        System.out.println(inversionCount(a, 0, a.length-1));
        assert(isSorted(a));
//        int[] a= {4,5,6,1,7,2,3};
//        System.out.println(inversionCount(a, 0, a.length-1));
//        for (int i: a) {
//            System.out.print(i+" ");
//        }
    }
}
