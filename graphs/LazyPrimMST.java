package graphs;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/***************************************
 * 切分定理：在一幅加权图中，给定任意的切分，它的横切边中的权重最小者必然属于图的最小生成树
 * 贪心法正确性证明的关键
 * @author Cello
 * 延时版Prim最小生成树算法
 ***************************************/
public class LazyPrimMST {
	private boolean[] marked;
	private Queue<Edge> mst;
	private PriorityQueue<Edge> pq;
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new PriorityQueue<Edge>();
		marked = new boolean[G.V()];
		mst = new LinkedList<Edge>();
		visit(G, 0);
		while(!pq.isEmpty()) {
			Edge e = pq.remove();
			int v = e.either(), w = e.other(v);
			if(marked[v] && marked[w])continue;
			mst.add(e);
			if(!marked[v]) visit(G,v);
			if(!marked[w]) visit(G,w);
		}
	}
	public Iterable<Edge> edges() {
		return mst;
	}
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for(Edge e: G.adj(v)){
			if(!marked[e.other(v)]){
				pq.add(e);
			}
		}
	}
}
