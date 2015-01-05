package graphs;

import java.util.ArrayList;
import java.util.List;

/*********************************************
 * 
 * @author Cello
 * Directed Graph
 *********************************************/
public class DiGraph {

	private final int V;
	private int E;
	private List<List<Integer>> adj;
	public DiGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = new ArrayList<List<Integer>>(V);
		for(int i = 0; i< V; i++) {
			adj.add(new ArrayList<Integer>());
		}
	}
	public int V() {
		return this.V;
	}
	public int E() {
		return this.E;
	}
	public void addEdge(int v, int w) {
		adj.get(v).add(w);
		this.E ++;
	}
	public Iterable<Integer> adj(int v) {
		return adj.get(v);
	}
	public DiGraph reverse() {
		DiGraph R = new DiGraph(this.V);
		for (int v = 0; v < V; v++) {
			for(int w: adj.get(v)) {
				R.addEdge(w,v);
			}
		}
		return R;
	}
}
