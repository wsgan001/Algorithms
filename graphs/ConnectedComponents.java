package graphs;
/***************************************
 * 
 * @author Cello
 * 连通分量，深度优先搜索的应用
 * 其实连通分量与Union find是同一个问题，使用DFS的CC也能解决union find的问题，
 * 但是连通分量的问题需要先构造图，不适合动态添加与删除；union find则比较适合动态增删
 **************************************/
public class ConnectedComponents {

	private boolean[] marked;
	private int[] id;
	private int count;
	public ConnectedComponents(Graph G) {
		this.marked = new boolean[G.V()];
		this.id = new int[G.V()];
		this.count = 0;
		for(int s = 0; s < G.V(); s ++) {
			if(marked[s]) continue;
			//每次从一个起点深度搜索都会将一个连通分量遍历到底
			dfs(G, s);
			count ++;
		}
	}
	private void dfs(Graph G, int s) {
		marked[s] = true;
		id[s] = count;
		for(int v: G.adj(s)){
			if(marked[v]) continue;
			dfs(G, v);
		}
	}
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	public int count() {
		return count;
	}
	public int id(int v) {
		return id[v];
	}
}
