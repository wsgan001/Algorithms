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
    public static void main (String[] args) {
        Enumeration enumeration = new Enumeration();
        List<List<Integer>> perms = enumeration.permutations(0);
        if (perms != null) {
            for (List<Integer> perm: perms) {
                for (int i: perm) {
                    System.out.print(i+" ");
                }
            }
            System.out.println();
        }
        perms = enumeration.permutations(1);
        if (perms != null) {
            for (List<Integer> perm: perms) {
                for (int i: perm) {
                    System.out.print(i+" ");
                }
                System.out.println();
            }
        }
        perms = enumeration.permutations(5);
        if (perms != null) {
            for (List<Integer> perm: perms) {
                for (int i: perm) {
                    System.out.print(i+" ");
                }
                System.out.println();
            }
        }
    }
}
