package sort;

import sun.jvm.hotspot.utilities.Assert;

/**
 * Created by basin on 16/2/15.
 */
public class Insertion {
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

    /**
     * Straight forward method of insert sort
     * @param A
     */
    public static void straightInsertSort(Comparable[] A) {
        if (A == null || A.length == 1) return;
        for (int i = 1; i < A.length; i++) {
            Comparable tmp = A[i];
            int j = i-1;
            for (j = i-1; j >= 0 && less(tmp, A[j]); j--){
                A[j+1] = A[j];
            }
            A[j+1]= tmp;
        }
    }

    /**
     * Use Binary Search to find the location to insert new item
     * @param A
     */
    public static void binarySearchInsertSort(Comparable[] A) {
        if (A == null || A.length == 1) return;
        for (int i = 1; i< A.length; i++) {
            Comparable tmp = A[i];
            int slot = binarySearch(A, 0, i-1, tmp);
            for (int j = i-1; j>= slot; j--) {
                A[j+1] = A[j];
            }
            A[slot] = tmp;
        }
    }
    private static int binarySearch(Comparable[] A, int l, int r, Comparable target) {
        if (l > r) return -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (less(target, A[mid])) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return l;
    }

    /**
     * Shell sort
     * @param A
     */
    public static void shellSort(Comparable[] A) {
        int gap = A.length;
        while (gap > 1) {
            gap = gap/3 + 1;
            shellInsert(A, gap);
        }
    }
    private static void shellInsert(Comparable[] A, int gap) {
        for (int i = gap; i<A.length; i++) {
            Comparable tmp = A[i];
            int j = i - gap;
            for (j = i-gap; j >= 0 && less(tmp, A[j]); j-=gap) {
                A[j+gap] = A[j];
            }
            A[j+gap] = tmp;
        }
    }
    public static void main (String[] args) {
        Integer[] A = {1,2,3,4,5,6,7};
        Integer[] B = {7,6,5,4,3,2,1};
        Integer[] C = {1,4,2,6,5,7,3};
        Integer[] D = {2,3,4,2,1,1,4,5,6,6,7,7,7,9};
//        Insertion.straightInsertSort(B);
//        System.out.println(Insertion.isSorted(B));
//        Insertion.straightInsertSort(C);
//        System.out.println(Insertion.isSorted(C));
//        Insertion.straightInsertSort(D);
//        System.out.println(Insertion.isSorted(D));
//        Insertion.binarySearchInsertSort(A);
//        System.out.println(Insertion.isSorted(A));
//        Insertion.binarySearchInsertSort(B);
//        System.out.println(Insertion.isSorted(B));
//        Insertion.binarySearchInsertSort(C);
//        System.out.println(Insertion.isSorted(C));
//        Insertion.binarySearchInsertSort(D);
//        System.out.println(Insertion.isSorted(D));
        Insertion.shellSort(A);
        System.out.println(Insertion.isSorted(A));
        Insertion.shellSort(B);
        System.out.println(Insertion.isSorted(B));
        Insertion.shellSort(C);
        System.out.println(Insertion.isSorted(C));
        Insertion.shellSort(D);
        System.out.println(Insertion.isSorted(D));
    }
}
