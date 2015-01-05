package graphs;
/****************************************
 * 
 * @author Cello
 * Topological sorting has two methods:1. the reverse post order od dfs;
 * 2. delete nodes with 0 in-degree
 ***************************************/
public class Topological {
	private Iterable<Integer> order;
	public Topological(DiGraph G) {
		DirectedCycle cycleFinder = new DirectedCycle(G);
		if(!cycleFinder.hasCycle()) {
			DirectedDFS dfs = new DirectedDFS(G);
			order = dfs.reversePost();
		}
	}
	public Iterable<Integer> order() {
		return order;
	}
	public boolean isDAG() {
		return order != null;
	}
}
