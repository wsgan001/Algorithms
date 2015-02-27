package graphs;

import java.util.ArrayList;
import java.util.List;

/**********************************
 * 
 * @author Cello
 * аз╫с╠М
 *********************************/
public class Graph {
	private final int V;
	private int E;
	private List<List<Integer>> adj;
	public Graph(int v) {
		this.V = v;
		this.E = 0;
		adj = new ArrayList<List<Integer>>();
		for(int i = 0; i< V; i ++) {
			adj.add(new ArrayList<Integer>());
		}
	}
	public int V() {
		return this.V;
	}
	public int E() {
		return E;
	}
	public void addEdge(int v, int w) {
		adj.get(v).add(w);
		adj.get(w).add(v);
		E ++;
	}
	public Iterable<Integer> adj(int v) {
		return adj.get(v);
	}
}
