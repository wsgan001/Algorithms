package graphs;
/***************************************
 * 
 * @author Cello
 * ��ͨ�������������������Ӧ��
 * ��ʵ��ͨ������Union find��ͬһ�����⣬ʹ��DFS��CCҲ�ܽ��union find�����⣬
 * ������ͨ������������Ҫ�ȹ���ͼ�����ʺ϶�̬�����ɾ����union find��Ƚ��ʺ϶�̬��ɾ
 **************************************/
public class ConnectedComponents {

	private boolean[] marked;
	private int[] id;
	private int count;
	public ConnectedComponents(Graph G) {
		this.marked = new boolean[G.V()];
		this.id = new int[G.V()];
		this.count = 0;
		for(int s = 0; s < G.V(); s ++) {
			if(marked[s]) continue;
			//ÿ�δ�һ���������������Ὣһ����ͨ������������
			dfs(G, s);
			count ++;
		}
	}
	private void dfs(Graph G, int s) {
		marked[s] = true;
		id[s] = count;
		for(int v: G.adj(s)){
			if(marked[v]) continue;
			dfs(G, v);
		}
	}
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	public int count() {
		return count;
	}
	public int id(int v) {
		return id[v];
	}
}
