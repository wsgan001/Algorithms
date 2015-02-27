package graphs;
/***************************
 * 
 * @author Cello
 * 一个很经典的优化问题，可以求无向图的连通分量问题
 * 加权的实现即在union时，强制将小树连接到大树的根上
 * 路径压缩：即在find的时候，将在路径上遇到的所有节点直接连接到根
 **************************/
public class UnionFind {

	private int[] id;
	private int[] sz;
	private int count;
	public UnionFind(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];
		for(int i = 0; i< N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	public int count(){return count;}
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	public int find(int p) {
		while (p!=id[p]) p =id[p];
		return p;
	}
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if(i == j) return;
		if(sz[i] < sz[j]){
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
		count --;
	}
}
