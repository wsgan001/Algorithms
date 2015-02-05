package stanford.quicksort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * **************************************************
 * Created by basin on 15-2-5.
 * **************************************************
 */
public class Main {
    private static int cmpCountQkSort(int[] a, int l, int r, int qid) {
        if (l >= r) return 0;
        int pivot = -1, cmps = r-l;
        if (qid == 1) {
            pivot = choosePivotI(a, l, r);
        } else if (qid == 2) {
            exch(a, l, r);
            pivot = l;
        } else {
            pivot = choosePivotII(a, l, r);
            exch(a, l, pivot);
            pivot = l;
        }
        int i = l+1;
        for (int j = l+1; j <=r; j++) {
            if (less(a, j, pivot)) {
                if(i!=j)exch(a, i, j);
                i ++;
            }
        }
        exch(a, pivot, i-1);
        int left = cmpCountQkSort(a, l, i-2, qid);
        int right = cmpCountQkSort(a, i, r, qid);
        return  left + cmps + right;
    }
    private static int choosePivotI(int[] a, int l, int r) {
        return l;
    }
    private static int choosePivotII(int[] a, int l, int r) {
        if (l+1 == r) return l;
        int i = l;
        int j = r;
        int k = l + (r-l)/2;
        if ((less(a, i ,j) && less(a, j, k)) || (less(a, k ,j) && less(a, j, i))) return j;
        if ((less(a, j, i) && less(a, i, k)) || (less(a, k, i) && less(a, i, j))) return i;
        return k;
    }
    private static boolean less(int[] a, int i, int j) {
        if (a[i] < a[j]) return true;
        return false;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i-1] > a[i]) return false;
        }
        return true;
    }
    public static void main (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File
                ("/home/basin/algs4/stanford/QuickSort.txt")));
        String line = reader.readLine();
        int[] a = new int[10000];
        int i = 0;
        while (line != null) {
            a[i++] = Integer.parseInt(line);
            line = reader.readLine();
        }
        reader.close();
//        System.out.println(cmpCountQkSort(a,0,a.length-1, 1));
//        assert(isSorted(a));
//        System.out.println(cmpCountQkSort(a,0,a.length-1, 2));
//        assert (isSorted(a));
        System.out.println(cmpCountQkSort(a,0,a.length-1,3));
        assert (isSorted(a));
//        int[] a = {4,5,6,1,2,3};
//        System.out.println(cmpCountQkSort(a, 0, a.length-1,1));
//        assert (isSorted(a));
    }
}
