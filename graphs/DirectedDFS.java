package graphs;

import java.util.Stack;

/**************************************
 * 
 * @author Cello
 *
 **************************************/
public class DirectedDFS {

	private boolean[] marked;
	private Stack<Integer> reversePost;
	public DirectedDFS(DiGraph G) {
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]){
				dfs(G,v);
			}
		}
	}
	public DirectedDFS(DiGraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	public DirectedDFS(DiGraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		for(int s: sources) {
			if(!marked[s]){
				dfs(G,s);
			}
		}
	}
	public boolean marked(int v) {
		return marked[v];
	}
	private void dfs(DiGraph G, int s) {
		marked[s] = true;
		for(int v: G.adj(s)) {
			if(!marked[v]){
				dfs(G,v);
			}
		}
		reversePost.push(s);
	}
	public Iterable<Integer> reversePost() {
		return reversePost;
	}
}
