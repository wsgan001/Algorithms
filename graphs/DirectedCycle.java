package graphs;

import java.util.Stack;

/*********************
 * 
 * @author Cello
 * cycle in a directed Graph
 ********************/
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	public DirectedCycle(DiGraph G) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		onStack = new boolean[G.V()];
	}
	private void dfs(DiGraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(int w: G.adj(v)) {
			if(this.hasCycle()) return;
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G,w);
			}else {
				if(onStack[w]){
					cycle = new Stack<Integer>();
					for(int s = v; s!=w; s = edgeTo[s]){
						cycle.push(s);
					}
					cycle.push(w);
					cycle.push(v);
				}
			}
		}
		onStack[v] = false;
	}
	public boolean hasCycle() {
		return cycle!=null;
	}
	public Iterable<Integer> cycle() {
		return cycle;
	}
}
