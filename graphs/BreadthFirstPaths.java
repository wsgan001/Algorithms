package graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/***********************
 * 
 * @author Cello
 * 
 * 广度优先搜索
 **********************/
public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	public BreadthFirstPaths(Graph G, int s) {
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		bfs(G, s);
	}
	private void bfs(Graph G, int s) {
		marked[s] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		while(!queue.isEmpty()) {
			int v = queue.poll();
			for(int w: G.adj(v)) {
				if(marked[w]) continue;
				edgeTo[w] = v;
				marked[w] = true;
				queue.add(w);
			}
		}
	}
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	public Iterable<Integer> pathTo(int v) {
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x!=s; x = edgeTo[x]) {
			path.add(x);
		}
		path.add(s);
		return path;
	}
}
