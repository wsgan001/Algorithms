package graphs;
/**********************************************
 * 
 * @author Cello
 * DFS BFS算法可以解决很多问题，如BFS可以解决埃尔德西数的问题，求最短路径；
 * DFS可以用来判断图中是否有环或是否是二部图（或者二着色问题）
 ***********************************************/
public class Cycle {

	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph G) {
		marked = new boolean[G.V()];
		hasCycle = false;
		for(int s = 0; s < G.E(); s ++) {
			if(marked[s]) continue;
			dfs(G, s, s);
		}
	}
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for(int w: G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w, v);
			} else {
				//如果访问过且不是其父节点，则有环
				if(w != u) hasCycle = true;
			}
		}
	}
	public boolean hasCycle() {
		return hasCycle;
	}
}
