package graphs;
/**********************************************
 * 
 * @author Cello
 * DFS BFS�㷨���Խ���ܶ����⣬��BFS���Խ�����������������⣬�����·����
 * DFS���������ж�ͼ���Ƿ��л����Ƿ��Ƕ���ͼ�����߶���ɫ���⣩
 ***********************************************/
public class Cycle {

	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph G) {
		marked = new boolean[G.V()];
		hasCycle = false;
		for(int s = 0; s < G.E(); s ++) {
			if(marked[s]) continue;
			dfs(G, s, s);
		}
	}
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for(int w: G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w, v);
			} else {
				//������ʹ��Ҳ����丸�ڵ㣬���л�
				if(w != u) hasCycle = true;
			}
		}
	}
	public boolean hasCycle() {
		return hasCycle;
	}
}
