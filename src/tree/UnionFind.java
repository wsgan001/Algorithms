package tree;

/**
 * Created by basin on 14/2/15.
 */
public class UnionFind {
    private int[] id;
    private int count;
    public UnionFind(int n) {
        id = new int[n];
        for (int i = 0; i < n; i ++) {
            id[i] = i;
        }
        count = n;
    }
    public void union(int i, int j) {
        int idx = find(i);
        int idy = find(j);
        if (idx == idy) return;
        id[idx] = idy;
        this.count --;
    }
    public int find(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }
    public boolean connected(int i, int j) {
        return find(i) == find(j);
    }
    public int components() {
        return count;
    }
    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(10);
        unionFind.union(0,1);
        unionFind.union(8,9);
        unionFind.union(5,6);
        unionFind.union(6,7);
        System.out.println(unionFind.components());
        System.out.println(unionFind.connected(5,7));
        unionFind.union(0,7);
        System.out.println(unionFind.connected(1,7));
    }
}
