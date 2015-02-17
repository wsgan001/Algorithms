package sort;

import java.util.Random;

/**
 * Created by basin on 16/2/15.
 */
public class Exchange {
    private static void swap(Comparable[] A, int i, int j) {
        Comparable t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    private static boolean less(Comparable[] A, int i, int j) {
        if (A[i].compareTo(A[j]) <= 0) return true;
        return false;
    }
    private static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b) < 0) return true;
        return false;
    }
    private static boolean isSorted(Comparable[] A) {
        for (int i = 1; i< A.length; i++) {
            if (less(A[i], A[i-1])) return false;
        }
        return true;
    }

    /**
     * Bubble sort
     * @param A
     */
    public static void bubbleSort(Comparable[] A) {
        for (int i = 0; i < A.length-1; i++) {
            boolean exchanged = false;
            for (int j = A.length-1; j > i; j -- ){
                if (less(A, j, j-1)) {
                    swap(A, j, j-1);
                    exchanged = true;
                }
            }
            if (!exchanged) return;
        }
    }

    /**
     * Quick Sort
     * @param A
     */
    public static void quickSort(Comparable[] A) {
        quickSort(A, 0, A.length-1);
    }
    private static void quickSort(Comparable[] A, int l, int r) {
        if (l >= r) return;
        int pivot = choosePivot(A, l, r);
        int index = partition(A, l, r, pivot);
        quickSort(A, l, index-1);
        quickSort(A, index+1, r);
    }
    private static int choosePivot(Comparable[] A, int l, int r) {
        return r;
    }
    private static int partition(Comparable[] A, int l, int r, int pivot) {
        swap(A, r, pivot);
        int i = l, j = r-1;
        while (i <= j) {
            while (i <= j && less(A, i, r)) i++;
            while (i <= j && less(A, r, j)) j--;
            if (i < j){
                swap(A, i ,j);
                i++;
                j--;
            }
        }
        if (less(A, r, i)) swap(A, i, r);
        return i;
    }
    public static void main (String[] args) {
        Integer[] A = {1,2,3,4,5,6,7};
        Integer[] B = {7,6,5,4,3,2,1};
        Integer[] C = {1,4,2,6,5,7,3};
        Integer[] D = {3,3,2,2,4,4,5,5,8,8,8,6,6};
//        Exchange.bubbleSort(A);
//        System.out.println(Exchange.isSorted(A));
//        Exchange.bubbleSort(B);
//        System.out.println(Exchange.isSorted(B));
//        Exchange.bubbleSort(C);
//        System.out.println(Exchange.isSorted(C));
//        Exchange.bubbleSort(D);
//        System.out.println(Exchange.isSorted(D));
        Exchange.quickSort(A);
        System.out.println(Exchange.isSorted(A));
        Exchange.quickSort(B);
        System.out.println(Exchange.isSorted(B));
        Exchange.quickSort(C);
        System.out.println(Exchange.isSorted(C));
        Exchange.quickSort(D);
        System.out.println(Exchange.isSorted(D));
    }
}
