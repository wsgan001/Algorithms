package string;

import java.util.LinkedList;
import java.util.Queue;

/**************************************
 * 
 * @author Cello
 * 三向单词查找树
 **************************************/
public class TST<Value> {
	private int N;
	private Node<Value> root;
	private static class Node<Value>{
		private char c;
		private Value val;
		private Node<Value> left, mid, right;
	}
	
	public int size() {
		return N;
	}
	
	public Value get(String key) {
		if(key == null) throw new NullPointerException();
		if(key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
		Node<Value> p = get(root, key, 0);
		if(p == null) return null;
		return p.val;
	}
	private Node<Value> get(Node<Value> p, String key, int d) {
		if(p == null) return null;
		char c = key.charAt(d);
		if(c < p.c) return get(p.left, key, d);
		else if(c > p.c) return get(p.right, key, d);
		else if(d < key.length()-1) return get(p.mid, key, d+1);
		else return p;
	}
	public void put(String s, Value val) {
		root = put(root, s, val, 0);
	}
	private Node<Value> put(Node<Value> p, String s, Value val, int d){
		char c = s.charAt(d);
		if(p == null) {
			p = new Node<Value>();
			N++;
			p.c = c;
		}
		if(c < p.c) p.left = put(p.left, s, val, d);
		else if( c > p.c) p.right = put(p.right, s, val, d);
		else if(d < s.length() - 1) p.mid = put(p.mid, s, val, d+1);
		else p.val = val;
		return p;
	}
	public String longestPrefixOf(String s) {
		if (s == null || s.length() == 0) return null;
		int length = 0;
		Node<Value> p = root;
		int i = 0;
		while(p != null && i < s.length()) {
			char c = s.charAt(i);
			if(c < p.c) p = p.left;
			else if(c > p.c) p = p.right;
			else {
				i++;
				if(p.val != null) length = i;
				p = p.mid;
			}
		}
		return s.substring(0, length);
	}
	public Iterable<String> keys() {
		Queue<String> results = new LinkedList<String>();
		collect(root, new StringBuilder(), results);
		return results;
	}
	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> results = new LinkedList<String>();
		Node<Value> p = get(root, prefix, 0);
		if(p == null) return results;
		if(p.val!=null)results.add(prefix);
		collect(p.mid, new StringBuilder(prefix), results);
		return results;
	}
	private void collect(Node<Value> p, StringBuilder prefix, Queue<String> results){
		if(p == null) return;
		collect(p.left, prefix, results);
		if(p.val != null) results.add(prefix.toString()+p.c);
		collect(p.mid, prefix.append(p.c), results);
		prefix.deleteCharAt(prefix.length()-1);
		collect(p.right, prefix, results);
	}
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> results = new LinkedList<String>();
		collect(root, new StringBuilder(), 0, pattern, results);
		return results;
	}
	private void collect(Node<Value> p, StringBuilder prefix, int i, String pattern, Queue<String> results){
		if(p == null) return;
		char c = pattern.charAt(i);
		if(c == '.' || c < p.c) collect(p.left, prefix, i, pattern, results);
		if(c == '.' || c == p.c) {
			if(i == pattern.length() - 1 && p.val != null) results.add(prefix.toString() + p.c);
			if(i < pattern.length() - 1) {
				collect(p.mid, prefix.append(c), i+1, pattern, results);
				prefix.deleteCharAt(prefix.length()-1);
			}
		}
		if(c == '.' || c > p.c) collect(p.right, prefix, i, pattern, results);
	}
}
