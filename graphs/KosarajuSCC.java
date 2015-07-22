public class KosarajuSCC {
	private int[] id;
	private int count;
	private boolean[] marked;
	public KosarajuSCC(DiGraph g) {
		this.id = new int[g.V()];
		this.marked = new boolean[g.V()];
		this.count = 0;
		DiGraph reverseG = g.reverse();
		Stack<Integer> reversePost = reverseG.reversePost();
		for (int w: reversePost) {
			if (!marked[w]) {
				dfs(g, w);
				count++;
			}
		}
	}
	private void dfs(DiGraph g, int w) {
		marked[w] = true;
		id[w] = count;
		for (int v: g.adj[w].neighbors) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
	}
	public boolean stronglyConnected(int w, int v) {
		return id[w] == id[v];
	}
	public int id(int w) {
		return this.id[w];
	}
	public int count() {
		return this.count;
	}
}