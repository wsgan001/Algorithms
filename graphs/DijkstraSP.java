/**
 * Dijkstra shortest path Algorithm
**/
public class DijkstraSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private IndexMinPQ<Double> pq;
	public DijkstraSP(DirectedGraph G, int s) {
		distTo = new double[G.V()];
		Arrays.fill(distTo, Double.MAX_VALUE);
		distTo[s] = 0.0;
		edgeTo = new DirectedEdge[G.V()];
		pq = new IndexMinPQ<Double>(G.V());

		pq.insert(s, 0.0);
		while (!pq.isEmpty()) {
			relax(G, pq.delMin());
		}
	}
	private void relax(DirectedGraph G, int v) {
		for (DirectedEdge e: G.adj[v]) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight) {
				distTo[w] = distTo[v] + e.weight;
				edgeTo[w] = e;
				if (pq.contains(w)) {
					pq.change(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
			}
		}
	}
}