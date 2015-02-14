package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by basin on 13/2/15.
 */
public class InOrder {
    // Recursive mode
    public List<Integer> inOrderRecursive(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        inOrder(root, nodes);
        return nodes;
    }
    private void inOrder(TreeNode p, List<Integer> nodes) {
        if (p == null) return;
        inOrder(p.left, nodes);
        nodes.add(p.val);
        inOrder(p.right, nodes);
    }
    // No Recursive
    public List<Integer> inOrderNoRecursive(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        if (root == null) return nodes;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                nodes.add(p.val);
                p = p.right;
            }
        }
        return nodes;
    }
    // Morris In-order Travel

    /**
     * 1. cur <- root
     * 2. if cur has left child, find cur's precursor pre: the most right child of cur's left subtree.
     * if pre's right child is null, set pre.right = cur; cur <- cur.left;
     * if pre's right child is cur, set pre.right = null; Visit cur node; cur <- cur.right
     * 3. if cur has no left child, cur <- cur.right
     * @param root
     * @return
     */
    public List<Integer> morrisInOrder (TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                nodes.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode p = cur.left;
                while (p.right != cur && p.right != null) {
                    p = p.right;
                }

                if (p.right == cur) {// has been visited
                    nodes.add(cur.val);
                    cur = cur.right;
                } else {// set the thread up
                    p.right = cur;
                    cur = cur.left;
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
        InOrder inOrder = new InOrder();
        List<Integer> nodes = inOrder.inOrderRecursive(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();

        nodes = inOrder.inOrderNoRecursive(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();

        nodes = inOrder.morrisInOrder(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
