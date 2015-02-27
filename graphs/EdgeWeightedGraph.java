package graphs;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {

	private final int V;
	private int E;
	private List<List<Edge>> adj;
	public EdgeWeightedGraph(int V) {
		this.V = V;
		adj = new ArrayList<List<Edge>>(V);
		for(int v = 0; v < V; v++){
			adj.add(new ArrayList<Edge>());
		}
	}
	public int V(){return V;}
	private int E(){return E;}
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj.get(v).add(e);
		adj.get(w).add(e);
		E++;
	}
	public Iterable<Edge> adj(int v) {
		return adj.get(v);
	}
	public Iterable<Edge> edges() {
		List<Edge> edgeList = new ArrayList<Edge>();
		for(int v = 0; v< V; v++) {
			for(Edge e: adj(v)){
				if(e.other(v) > v) edgeList.add(e);
			}
		}
		return edgeList;
	}
}
