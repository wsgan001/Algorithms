public class Huffman {
	private static class Node {
		char ch;
		int freq;
		boolean isLeaf;
		Node left, right;
		public Node(char ch, int freq, boolean isLeaf) {
			this.ch = ch;
			this.freq = freq;
			this.isLeaf = isLeaf;
		}
	}
	private HashMap<Character, Integer> getSymbols(String text) {
		HashMap<Character, Integer> symbols = new HashMap<Character, Integer>();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (symbols.containsKey(ch)) {
				symbols.put(ch, symbols.get(ch) + 1);
			} else {
				symbols.put(ch, 1);
			}
		}
		return symbols;
	}
	private Node buildTrie(HashMap<Character, Integer> symbols) {
		Node root = new Node('\0', -1);
		PriorityQueue<Node> minHeap = new PriorityQueue<Node>(symbols.size()+1, new Comparator<Node>(){
			public int compare(Node n1, Node n2) {
				return n1.freq - n2.freq;
			}
		});
		for (char ch: symbols.keySet()) {
			minHeap.offer(new Node(ch, symbols.get(ch), true));
		}
		while (minHeap.size() > 1) {
			Node n1 = minHeap.poll();
			Node n2 = minHeap.poll();
			Node n3 = new Node('\0', n1.freq + n2.freq, false);
			minHeap.insert(n3);
		}
		return minHeap.poll();
	}
	private HashMap<Character, String> getEncodes(Node root) {
		HashMap<Character, String> encodes = new HashMap<Character, String>();
		
		getEncodes(root, "", encodes);
		return encodes;
	}
	private void getEncodes(Node root, String s, HashMap<Character, String> encodes) {
		if (root == null) return;
		if (root.isLeaf) {
			encodes.put(root.char, s;
			return;
		}
		getEncodes(root.left, s+"0", encodes);
		getEncodes(root.right, s+"1", encodes);
	}
	private String writeTrie(Node root) {
		if (root.isLeaf) {
			return "1" + root.ch.toBinary();
		}
		return "0" + writeTrie(root.left) + writeTrie(root.right);
	}
	public String compress(String text) {
		if (text == null || text.length() == 0) return "";
		// statistic the character's occurance
		HashMap<Character, Integer> symbols = getSymbols(text);
		Node root = buildTrie(symbols);
		// get encoded table
		HashMap<Character, String> encodes = getEncodes(Node root);
		StringBuilder sb = new StringBuilder();
		sb.append(writeTrie(root));
		for (int i = 0; i < text.length(); i++) {
			sb.append(encodes.get(text.charAt(i)));
		}
		return sb.toString();
	}
	public String extend(String code) {
		Node root = readTrie();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < code.length(); i++) {
			Node x = root;
			while (!x.isLeaf) {
				if (code.charAt(i) == '0') {
					x = x.left;
				} else {
					x = x.right;
				}
			}
			sb.append(x.ch);
		}
		return sb.toString();
	}
}