package graphs;

import java.util.LinkedList;
import java.util.Queue;

import sorting.IndexMinPQ;

/************************************
 * 
 * @author Cello
 * Prim 最小生成树，即时版
 *************************************/
public class PrimMst {

	private Edge[] edgeTo;//距离树最近的边
	private double[] distTo;// distTo[w] = edgeTo[w].weight()
	private boolean[] marked;// if v is in MST, marked[v] = true
	private IndexMinPQ<Double> pq;// 有效的横切边
	public PrimMst(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		for(int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
		distTo[0] = 0.0;
		pq.insert(0, 0.0);
		while(!pq.isEmpty()) {
			visit(G, pq.delMin());
		}
	}
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for(Edge e: G.adj(v)) {
			int w = e.other(v);
			if(marked[w]) continue;
			if(e.weight() < distTo[w]) {
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if(pq.contains(w))pq.decreaseKey(w, distTo[w]);
				else pq.insert(w, distTo[w]);
			}
		}
	}
	public Iterable<Edge> edges() {
		Queue<Edge> mst = new LinkedList<Edge>();
		for(int v = 0; v < edgeTo.length; v++) {
			Edge e = edgeTo[v];
			if(e != null) {
				mst.add(e);
			}
		}
		return mst;
	}
}
