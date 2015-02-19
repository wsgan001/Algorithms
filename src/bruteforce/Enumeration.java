package bruteforce;

import tree.IntervalTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basin on 18/2/15.
 */
public class Enumeration {
    public List<List<Integer>> permutations(int n) {
        if (n <= 0) return null;
        List<List<Integer>> perms = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        permutations(perms, perm, n);
        return perms;
    }
    private void permutations(List<List<Integer>> perms, List<Integer> perm, int n) {
        if (perm.size() == n) {
            perms.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (perm.contains(i)) continue;
            perm.add(i);
            permutations(perms, perm, n);
            perm.remove(perm.size()-1);
        }
    }

    /**
     * 1. from right to left, find the first number a which break the order of increasing.
     * 2. from right to left, find the minimal number b while b > a
     * 3. swap a and b
     * 4. reverse the numbers on the right of partition index
     * @param n
     * @param A
     * @return the next permutation of A
     */
    public void nextPermutation(int n, int[] A) {
        if (n <= 0 || A == null || A.length != n) throw new IllegalArgumentException();
        int partitionIndex = A.length-1;
        while (partitionIndex > 0 && A[partitionIndex] < A[partitionIndex-1]) partitionIndex--;
        partitionIndex--;
        if (partitionIndex < 0) {
            for (int i = 1; i <= n; i++) A[i-1] = i;
            return;
        }
        int minIndex = -1;
        for (int i = A.length-1; i >=partitionIndex; i--) {
            if (A[i] > A[partitionIndex]) {
                if (minIndex < 0) minIndex = i;
                else if (A[minIndex] > A[i]){
                    minIndex = i;
                }
            }
        }
        swap(A, partitionIndex, minIndex);
        // reverse partitionIndex+1----> A.length-1
        reverse(A, partitionIndex+1, A.length-1);
    }
    private void reverse(int[]A, int start, int end) {
        while (start < end) {
            int t = A[start];
            A[start] = A[end];
            A[end] = t;
            start++;
            end--;
        }
    }
    private void swap (int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    public static void main (String[] args) {
        Enumeration enumeration = new Enumeration();
//        List<List<Integer>> perms = enumeration.permutations(0);
//        if (perms != null) {
//            for (List<Integer> perm: perms) {
//                for (int i: perm) {
//                    System.out.print(i+" ");
//                }
//            }
//            System.out.println();
//        }
//        perms = enumeration.permutations(1);
//        if (perms != null) {
//            for (List<Integer> perm: perms) {
//                for (int i: perm) {
//                    System.out.print(i+" ");
//                }
//                System.out.println();
//            }
//        }
//        perms = enumeration.permutations(5);
//        if (perms != null) {
//            for (List<Integer> perm: perms) {
//                for (int i: perm) {
//                    System.out.print(i+" ");
//                }
//                System.out.println();
//            }
//        }
        int[] A = {5,4,3,2,1};
        enumeration.nextPermutation(5, A);
        for(int i: A){
            System.out.print(i +" ");
        }
        System.out.println();
    }
}
