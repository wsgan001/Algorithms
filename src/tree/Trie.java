package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by basin on 15/2/15.
 */
public class Trie {
    private static int CHARCOUNT = 10;
    private static class Node {
        public int count = 0;
        public Node[] nodes;
        public Node() {
            nodes = new Node[CHARCOUNT];
        }
    }
    private Node root;
    public Trie() {
        root = new Node();
    }
    public void insert(String word) {
        Node p = root;
        for (int i = 0; i< word.length(); i++) {
            int index = word.charAt(i) - '0';
            if (p.nodes[index] == null) {
                p.nodes[index] = new Node();
            }
            p = p.nodes[index];
        }
        p.count ++;
    }
    public boolean isPrefix(String prefix) {
        Node p = root;
        for (int i = 0; i< prefix.length(); i++) {
            if (p.nodes[prefix.charAt(i)-'0'] == null) return false;
            p = p.nodes[prefix.charAt(i)-'0'];
        }
        for (int i = 0; i< CHARCOUNT; i++) {
            if (p.nodes[i] != null) return true;
        }
        return false;
    }
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int i = 1;
        while(true) {
            boolean f = false;
            Trie trie = new Trie();
            List<String> wordList = new ArrayList<String>();
            String word = in.nextLine();
            wordList.add(word);
            while (!word.equals("9")) {
                trie.insert(word);
                word = in.nextLine();
                wordList.add(word);
            }
            for (String w: wordList) {
                if (trie.isPrefix(w)) {
                    f = true;
                    break;
                }
            }
            if (f) {
                System.out.println("Set "+i+" is not immediately decodable");
            } else {
                System.out.println("Set "+i+" is immediately decodable");
            }
            i++;
        }
    }
}
