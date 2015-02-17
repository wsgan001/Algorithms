package sort;

/**
 * Created by basin on 16/2/15.
 */
public class Selection {
    private static void swap(Comparable[] A, int i, int j) {
        Comparable t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    private static boolean less(Comparable[] A, int i, int j) {
        if (A[i].compareTo(A[j]) < 0) return true;
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
    public static void simpleSelectionSort(Comparable[] A) {
        if (A == null || A.length <= 1) return;
        for (int i = 0; i < A.length - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < A.length; j++) {
                if (less(A, j, minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) swap(A, i, minIndex);
        }
    }
    public static void main(String[] args) {
        Integer[] A = {1,2,3,4,5,6,7};
        Integer[] B = {7,6,5,4,3,2,1};
        Integer[] C = {1,4,2,6,5,7,3};
        Integer[] D = {3,3,2,2,4,4,5,5,8,8,8,6,6};
        Selection.simpleSelectionSort(A);
        System.out.println(Selection.isSorted(A));
        Selection.simpleSelectionSort(B);
        System.out.println(Selection.isSorted(B));
        Selection.simpleSelectionSort(C);
        System.out.println(Selection.isSorted(C));
        Selection.simpleSelectionSort(D);
        System.out.println(Selection.isSorted(D));
    }
}
