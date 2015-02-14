package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by basin on 13/2/15.
 */
public class PreOrder {
    // recursive mode
    public List<Integer> preOrderRecursive(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        preOrder(root, nodes);
        return nodes;
    }
    private void preOrder(TreeNode p, List<Integer> nodes) {
        if (p == null) return;
        nodes.add(p.val);
        preOrder(p.left, nodes);
        preOrder(p.right, nodes);
    }
    // no recursive
    public List<Integer> preOrderNoRecursive(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        if (root == null) return nodes;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode p = stack.pop();
            nodes.add(p.val);
            if (p.right != null) stack.push(p.right);
            if (p.left != null) stack.push(p.left);
        }
        return nodes;
    }
    // Morris' Travel

    /**
     * 1. cur<-root
     * 2.  if cur has no left child, visit cur and set cur <- cur.right; if cur has left child, find the precursor
     * of cur, it's the most right node in cur's left half;
     * 3. if the cur's precursor has no right child, set its right child to cur, Then visit cur node and set cur <- cur.left
     * if cur's precursor has right child and its right child is cur, then reset its right child to null, and cur <- cur.right
     * @param root
     * @return
     */
    public List<Integer> morrisPreOrder(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                nodes.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode p = cur.left;
                while (p.right != null && p.right != cur) {
                    p = p.right;
                }
                if (p.right == null) {
                    nodes.add(cur.val);
                    p.right = cur;
                    cur = cur.left;
                } else {
                    p.right = null;
                    cur = cur.right;
                }
            }
        }
        return nodes;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode p1 = new TreeNode(3);
        TreeNode p2 = new TreeNode(2);
        TreeNode p3 = new TreeNode(1);
        TreeNode p4 = new TreeNode(5);
        root.left = p1;
        root.right = p4;
        p1.left = p2;
        p1.right = p3;
        PreOrder preOrder = new PreOrder();
        List<Integer> nodes = preOrder.preOrderRecursive(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();

        nodes = preOrder.preOrderNoRecursive(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();

        nodes = preOrder.morrisPreOrder(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
    }
}
