package sort;

/**
 * Created by basin on 17/2/15.
 */
public class MergeSort {
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
    public static void mergeSort(Comparable[] A) {
        if (A == null || A.length <= 1) return;
        mergeSort(A, 0 , A.length-1);
    }
    private static void mergeSort(Comparable[] A, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l)/2;
        mergeSort(A, l, mid);
        mergeSort(A, mid + 1, r);
        merge(A, l, mid, r);
    }
    private static void merge(Comparable[] A, int l, int mid, int r) {
        if (l >= r) return;
        Comparable[] B = new Comparable[A.length];
        for (int i = l; i<= r; i++) {
            B[i] = A[i];
        }
        int i = l, j = mid+1, k = l;
        for (i = l, j = mid+1,k = l; i<=mid && j<=r; k++) {
            if (less(B, i,j)) {
                A[k] = B[i++];
            } else {
                A[k] = B[j++];
            }
        }
        while (i<=mid) A[k++] = B[i++];
        while (j<=r) A[k++] = B[j++];
    }
    public static void main (String[] args) {
        Integer[] A = {1,2,3,4,5,6,7};
        Integer[] B = {7,6,5,4,3,2,1};
        Integer[] C = {1,4,2,6,5,7,3};
        Integer[] D = {3,3,2,2,4,4,5,5,8,8,8,6,6};
        MergeSort.mergeSort(A);
        System.out.println(MergeSort.isSorted(A));
        MergeSort.mergeSort(B);
        System.out.println(MergeSort.isSorted(B));
        MergeSort.mergeSort(C);
        System.out.println(MergeSort.isSorted(C));
        MergeSort.mergeSort(D);
        System.out.println(MergeSort.isSorted(D));
    }
}
