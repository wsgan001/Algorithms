package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by basin on 14/2/15.
 */
public class PostOrder {
    // Recursive mode
    public List<Integer> postOrderRecursive(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        postOrder(root, nodes);
        return nodes;
    }
    private void postOrder(TreeNode p, List<Integer> nodes) {
        if (p == null)  return;
        postOrder(p.left, nodes);
        postOrder(p.right, nodes);
        nodes.add(p.val);
    }
    // No Recursive
    public List<Integer> postOrderNoRecursive(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        if (root == null) return nodes;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root, q = null;// q : the last visited node
        do {
            // put the left half into stack
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            q = null;
            while (!stack.isEmpty()) {
                p = stack.pop();
                if (p.right == q) {// the right is null or visited, then visit p
                    nodes.add(p.val);
                    q = p;
                } else {// Otherwise, put p into stack again, travel the right half
                    stack.push(p);
                    p = p.right;
                    break;
                }
            }
        }while (!stack.isEmpty());
        return nodes;
    }
    // Morris Travel
    /**
     * Frankly, I think the post order morris is not practical. It's not efficient.
     * 1. new a dummy node, set dummy.left = root
     * 2. cur <- dummy
     * 3. if cur.left == null, cur <- cur.right
     * if cur.left != null, find cur's precursor: the right most of cur's left half subtree.
     * if pre.right == null, set the thread up. then cur<-cur.left;
     * else if pre.right == cur, set pre.right == null. And print the path from cur.left to pre in reversed order.
     * cur <- cur.right;
     * @param root
     * @return nodes
     */
    public List<Integer> morrisPostOrder(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        TreeNode dummy = new TreeNode(Integer.MIN_VALUE);
        dummy.left = root;
        TreeNode cur = dummy;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode p = cur.left;
                while (p.right != null && p.right != cur) p = p.right;
                if (p.right == null) {
                    p.right = cur;
                    cur = cur.left;
                } else {
                    p.right = null;
                    // output nodes in reversed order
                    travelInReverse(cur.left, p, nodes);
                    cur = cur.right;
                }
            }
        }
        return nodes;
    }
    // 1. Reverse the path from p->q
    // 2. travel the path
    // 3. reset the path
    private void travelInReverse(TreeNode p, TreeNode q, List<Integer> nodes) {
        if (p == q) {
            nodes.add(p.val);
            return;
        }
        TreeNode last = p, cur = p.right, next = cur.right;
        if (next == null) {
            nodes.add(cur.val);
            nodes.add(last.val);
            return;
        }
        // reverse the path
        p.right = null;
        while (next != null) {
            cur.right = last;
            last = cur;
            cur = next;
            next = next.right;
        }
        cur.right = last;
        while (cur != p) {
            nodes.add(cur.val);
            cur = cur.right;
        }
        nodes.add(p.val);
        // reset the path
        last = q; cur = q.right; next = cur.right;
        q.right = null;
        while (next != null) {
            cur.right = last;
            last = cur;
            cur = next;
            next = next.right;
        }
        cur.right = last;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode p1 = new TreeNode(3);
        TreeNode p2 = new TreeNode(2);
        TreeNode p3 = new TreeNode(1);
        TreeNode p4 = new TreeNode(5);
        TreeNode p5 = new TreeNode(0);
        root.left = p1;
        root.right = p4;
        p1.left = p2;
        p1.right = p3;
        p3.right = p5;
        PostOrder postOrder = new PostOrder();
        List<Integer> nodes = postOrder.postOrderRecursive(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();

        nodes = postOrder.postOrderNoRecursive(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();

        nodes = postOrder.morrisPostOrder(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();
        // Unit Test for travelInReverse
//        TreeNode s1 = new TreeNode(1);
//        TreeNode s2 = new TreeNode(2);
//        TreeNode s3 = new TreeNode(3);
//        TreeNode s4 = new TreeNode(4);
//        TreeNode s5 = new TreeNode(5);
//        s1.right = s2;
//        s2.right = s3;
//        s3.right = s4;
//        s4.right = s5;
//        List<Integer> path = new ArrayList<Integer>();
//        postOrder.travelInReverse(s1, s5, path);
//        for (int i: path) {
//            System.out.print(i + " ");
//        }
    }
}
