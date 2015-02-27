package graphs;
/********************************
 * 
 * @author Cello
 * DFS的应用，二部图的判别
 *******************************/
public class TwoColor {
	private boolean[] marked;
	private boolean[] color;
	private boolean isBipart = true;
	
	public TwoColor(Graph G) {
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s]) {
				dfs(G, s);
			}
		}
	}
	private void dfs(Graph G, int s) {
		marked[s] = true;
		for(int v: G.adj(s)) {
			if(!marked[v]) {
				color[v] = !color[s];
				dfs(G, v);
			} else {
				if(color[s] == color[v]) {
					isBipart = false;
				}
			}
		}
	}
	public boolean isBipart() {
		return isBipart;
	}
}
