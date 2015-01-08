package string;

import java.util.LinkedList;
import java.util.Queue;

/*************************************
 * 
 * @author Cello
 * RœÚµ•¥ ≤È’“ ˜
 *************************************/
public class TrieST<Value> {
	private static int R = 256;
	private int N;
	private Node root;
	private static class Node{
		private Object val;
		private Node[] next = new Node[R];
	}
	public Value get(String key) {
		Node x = get(root, key, 0);
		if(x == null) return null;
		return (Value)x.val;
	}
	private Node get(Node p, String key, int d) {
		if(p == null) return null;
		if(d == key.length()) return p;
		char c = key.charAt(d);
		return get(p.next[c], key, d+1);
	}
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	private Node put(Node p, String key, Value val, int d) {
		if(p == null) p = new Node();
		if(d == key.length()) {
			if(p.val == null) N++;
			p.val = val;
			return p;
		}
		char c = key.charAt(d);
		p.next[c] = put(p.next[c], key, val, d+1);
		return p;
	}
	public int size() {
		return N;
	}
	public boolean isEmpty() {
		return size() == 0;
	}
	/**
	 * 
	 * @return all keys in the symbol table as an Iterable set
	 */
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}
	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> results = new LinkedList<String>();
		Node p = get(root, prefix, 0);
		collect(p, new StringBuilder(prefix), results);
		return results;
	}
	private void collect(Node p, StringBuilder prefix, Queue<String> results){
		if(p == null) return;
		if(p.val != null) results.add(prefix.toString());
		for(char c = 0; c < R; c++) {
			prefix.append(c);
			collect(p.next[c], prefix, results);
			prefix.deleteCharAt(prefix.length()-1);
		}
	}
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> results = new LinkedList<String>();
		collect(root, new StringBuilder(), pattern, results);
		return results;
	}
	private void collect(Node p, StringBuilder prefix, String pattern, Queue<String> results) {
		if(p == null) return;
		int d = prefix.length();
		if(d == pattern.length() && p.val != null){
			results.add(prefix.toString());
		}
		if(d == pattern.length()) return;
		char c = pattern.charAt(d);
		if(c == '.') {
			for(char ch = 0; ch < R; ch ++) {
				prefix.append(ch);
				collect(p.next[ch], prefix, pattern, results);
				prefix.deleteCharAt(prefix.length()-1);
			}
		} else {
			prefix.append(c);
			collect(p.next[c], prefix, pattern, results);
			prefix.deleteCharAt(prefix.length()-1);
		}
	}
	public String longestPrefixOf(String query) {
		int length = longestPrefixOf(root, query, 0, 0);
		return query.substring(0, length);
	}
	private int longestPrefixOf(Node p, String query, int d, int length) {
		if(p == null) return length;
		if(p.val != null) length = d;
		if(d == query.length()) return length;
		char c = query.charAt(d);
		return longestPrefixOf(p.next[c], query, d+1, length);
	}
	public void delete(String key) {
		root = delete(root, key, 0);
	}
	private Node delete(Node p, String key, int d) {
		if(p == null) return null;
		if(d == key.length()){
			if(p.val != null) N--;
			p.val = null;
		} else {
			char c = key.charAt(d);
			p.next[c] = delete(p.next[c], key, d+1);
		}
		// remove subtrie rooted at x if its is completely empty
		if(p.val != null) return p;
		for(int c = 0; c< R; c++){
			if(p.next[c] != null) return p;
		}
		return null;
	}
}
