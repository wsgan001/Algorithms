package graphs;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/***********************************
 * 
 * @author Cello
 * Krusal �㷨, ��Prim��ȣ�Krusal ��ÿ�ζ���ӱߣ���Prim����ӵ�
 ***********************************/
public class KruskalMST {

	private Queue<Edge> mst;
	public KruskalMST(EdgeWeightedGraph G) {
		mst = new LinkedList<Edge>();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(Edge e: G.edges()) {
			pq.add(e);
		}
		UnionFind uf = new UnionFind(G.V());
		while(!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.remove();
			int v=e.either(), w = e.other(v);
			if(uf.connected(v, w)) continue;
			uf.union(v,w);
			mst.add(e);
		}
	}
	public Iterable<Edge> edges() {
		return mst;
	}
}
