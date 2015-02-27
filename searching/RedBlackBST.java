package searching;
/*****************************************
 * 
 * @author Cello
 * Red-black tree. It's easier to understand it from 2-3 tree.
 * More need to be learned, since why 2-3 tree, not 3-tree?
 * What's the difference? How to understand B-Tree and B-plus tree?
 ****************************************/
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;

	private class Node {
		Key key;
		Value val;
		Node left, right;
		boolean color;
		int N;// the count of nodes in this subtree.
		
		public Node(Key key, Value value, boolean color, int N) {
			this.key = key;
			this.val = value;
			this.color = color;
			this.N = N;
		}
	}
	private Node rotateLeft(Node p) {
		Node x = p.right;
		p.right = x.left;
		x.left = p;
		x.color = p.color;
		p.color = RED;
		x.N = p.N;
		p.N = 1 + size(p.left) + size(p.right);
		return x;
	}
	
	public void put(Key key, Value value) {
		root = put(key,  value,  root);
		root.color = BLACK;
	}
	private Node put(Key key, Value value, Node p) {
		if(p == null) {
			return new Node(key, value, RED, 1);
		}
		int cmp = key.compareTo(p.key);
		if( cmp < 0) {
			p.left = put(key, value, p.left);
		} else if( cmp > 0) {
			p.right = put(key, value, p.right);
		} else {
			p.val = value;
		}
		if(isRed(p.right) && !(isRed(p.left))) p = rotateLeft(p);
		if(isRed(p.left) && isRed(p.left.left)) p = rotateRight(p);
		if(isRed(p.left) && isRed(p.right)) flipColors(p);
		p.N = size(p.left) + size(p.right) + 1;
		return p;
	}
	private Node rotateRight(Node p) {
		Node x = p.left;
		p.left = x.right;
		x.right = p;
		x.color = p.color;
		p.color = RED;
		x.N = p.N;
		p.N = 1 + size(p.left) + size(p.right);
		return x;
	}
	private void flipColors(Node p) {
		p.color = RED;
		p.left.color = BLACK;
		p.right.color = BLACK;
	}
	private int size(Node p) {
		if(p == null) return 0;
		else return p.N;
	}
	private boolean isRed(Node x) {
		if(x == null) return false;
		return x.color == RED;
	}
	
}
