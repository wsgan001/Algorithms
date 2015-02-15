package tree;

import java.util.Scanner;

/**
 * Created by basin on 14/2/15.
 */
public class IntervalTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), Q = in.nextInt();
        int[] A = new int[N+1];
        for (int i = 1; i <= N; i ++) {
            A[i] = in.nextInt();
        }
        IntervalTree intervalTree = new IntervalTree(A);
        for (int i = 0; i< Q; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            System.out.println(intervalTree.query(l,r));
        }
    }
    private int[] A;
    private Node[] nodes;
    private int maxx;
    private int minx;
    private static class Node {
        int left, right;
        int max, min;
    }
    public IntervalTree(int[] A) {
        this.A = A;
        this.nodes = new Node[(A.length+1)*4+1];
        for (int i = 0; i< 4*(A.length+1)+1; i++) {
            this.nodes[i] = new Node();
        }
        build(1, 1, A.length-1);
    }

    /**
     * based on the root t, build a interval tree of A[l,r]
     * @param t
     * @param l
     * @param r
     */
    private void build(int t, int l, int r) {
        this.nodes[t].left = l;
        this.nodes[t].right = r;
        if (l == r) {
            nodes[t].max = nodes[t].min = A[l];
            return;
        }
        int mid = l + (r-l)/2;
        build(t*2, l, mid);
        build(t*2+1, mid+1, r);
        nodes[t].max = Math.max(nodes[2*t].max, nodes[2*t+1].max);
        nodes[t].min = Math.min(nodes[2*t].min, nodes[2*t+1].min);
    }
    public int query(int l, int r) {
        this.maxx = Integer.MIN_VALUE;
        this.minx = Integer.MAX_VALUE;
        maxDiff(1, l, r);
        return this.maxx - this.minx;
    }
    /**
     * based on root t, query the Max-Min of A[l,r]
     * @param t
     * @param l
     * @param r
     */
    private void maxDiff(int t, int l, int r) {
        if (nodes[t].left == l && nodes[t].right == r) {
            maxx = Math.max(nodes[t].max, maxx);
            minx = Math.min(nodes[t].min, minx);
            return;
        }
        int mid = nodes[t].left + (nodes[t].right - nodes[t].left)/2;
        if (l > mid) {// right child
            maxDiff(2*t+1, l, r);
        } else if (r <= mid) {//left child
            maxDiff(t*2, l, r);
        } else {
            maxDiff(2*t, l, mid);
            maxDiff(2*t+1, mid+1, r);
        }
    }
}
