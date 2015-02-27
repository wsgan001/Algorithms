package searching;


/********************************************
 * 
 * @author Cello
 * 仅打算实现二叉搜索树，红黑树和Hash读书收获也很大，对红黑树的理解从2-3树
 * 开始更容易些
 *********************************************/
public class BST<Key extends Comparable<Key>, Value> {
	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int N; // number of nodes in subtree
		
		public Node(Key key, Value value, int N) {
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}
	private Node root;
	/**
	 * 插入操作：1. 如果Key已经存在，则更新value
	 * 2. 寻找到地，新建node
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value) {
		root = put(root, key, value);
		assert check();
	}
	private Node put(Node p, Key key, Value value) {
		if(p == null) {
			p = new Node(key, value, 1);
		}
		int cmp = key.compareTo(p.key);
		if(cmp < 0 ) {
			p.left = put(p.left, key, value);
		} else if (cmp > 0) {
			p.right = put(p.right, key, value);
		} else {
			p.value = value;
		}
		p.N = size(p.left) + size(p.right) +1;
		return p;
	}
	public Value get(Key key) {
		return get(root, key);
	}
	private Value get(Node p, Key key) {
		if(p == null) return null;
		int cmp = p.key.compareTo(key);
		if(cmp < 0) return get(p.right, key);
		else if(cmp > 0) return get(p.left, key);
		else {
			return p.value;
		}
	}
	/**
	 * 删除操作
	 * @param key
	 */
	public void delete(Key key) {
		root = delete(root, key);
		assert check();
	}
	private Node delete(Node p, Key key) {
		if(p == null) return null;
		int cmp = p.key.compareTo(key);
		if(cmp > 0) {
			p = delete(p.left, key);
		} else if (cmp < 0) {
			p = delete(p.right, key);
		} else {
			//先判断边界情况，是个好的习惯
			if(p.right == null) return p.left;
			if(p.left == null) return p.right;
			// 注意返回值的变化
			Node tNode = p;
			p = min(tNode.right);
			
			p.right = deleteMin(tNode.right);
			p.left = tNode.left;
		}
		p.N = size(p.left) + size(p.right) + 1;
		return p;
	}
	private Node deleteMax(Node p) {
		if(p == null) return null;
		if(p.right == null) return p.left;
		p.right = deleteMax(p.right);
		p.N = size(p.left) + size(p.right) + 1;
		return p;
	}
	private Node deleteMin(Node p) {
		if(p == null) return null;
		if(p.left == null) return p.right;
		p.left = deleteMin(p.left);
		p.N = size(p.left) + size(p.right) + 1;
		return p;
	}
	public boolean contains(Key key) {
		return get(key)!=null;
	}
	public boolean isEmpty() {
		return size() == 0;
	}
	public int size() {
		return size(root);
	}
	private int size(Node p) {
		if(p == null) return 0;
		return p.N;
	}
	public Key min() {
		return min(root).key;
	}
	private Node min(Node p) {
		if(p == null) return null;
		while(p.left != null) {
			p = p.left;
		}
		return p;
	}
	public Key max() {
		return max(root).key;
	}
	private Node max(Node p) {
		if(p == null) return null;
		while(p.right != null) {
			p = p.right;
		}
		return p;
	}
	public int rank(Key key) {
		return rank(root, key);
	}
	private int rank(Node p, Key key) {
		if(p == null) return 0;
		int cmp = key.compareTo(p.key);
		if(cmp < 0) {
			return rank(p.left, key);
		} else if(cmp > 0) {
			return size(p.left) + rank(p.right, key) + 1;
		} else {
			return size(p.left);
		}
	}
	public Key select(int k) {
		return select(root, k);
	}
	private Key select(Node p, int k) {
		if(p == null) return null;
		int t = size(p);
		if(t == k) return p.key;
		else if(t > k) {
			return select(p.left, k);
		}else {
			return select(p.right, k);
		}
	}
	private boolean less(Key k1, Key k2) {
		return k1.compareTo(k2) < 0;
	}
	private boolean check() {
		return check(root);
	}
	private boolean check(Node p) {
		if(p == null) return true;
		if(p.left!=null && less(p.key, p.left.key)) return false;
		if(p.right!= null && less(p.right.key, p.key)) return false;
		return check(p.left) && check(p.right);
	}
}
