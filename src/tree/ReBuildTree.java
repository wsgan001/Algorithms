package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basin on 14/2/15.
 */
public class ReBuildTree {
    // build the post-order list from pre-order and in-order list
    public List<Integer> buildPostOrder(int[] preOrder, int[] inOrder) {
        List<Integer> postOrder = new ArrayList<Integer>();
        build(postOrder, preOrder, inOrder, 0, preOrder.length-1, 0, inOrder.length - 1);
        return postOrder;
    }
    private void build(List<Integer> postOrder, int[] preOrder, int[] inOrder, int l1, int r1, int l2, int r2) {
        if (l1 > r1 || l2 > r2) return;
        if (l1 == r1) {postOrder.add(preOrder[l1]); return;}
        int root = preOrder[l1];
        int mid = -1;
        // find the index of root in in-order array
        for (int i = l2; i <= r2; i++) {
            if (inOrder[i] == root) {
                mid = i;
                break;
            }
        }
        int leftLen = mid - l2;
        // construct the left half
        build(postOrder, preOrder, inOrder, l1+1, l1+leftLen, l2, mid-1);
        // construct the right half
        build(postOrder, preOrder, inOrder, l1+1+leftLen, r1, mid+1, r2);
        // add the cur node
        postOrder.add(root);
    }
    public TreeNode reBuild(int[] preOrder, int[] inOrder) {
        return reBuild(preOrder, inOrder, 0, preOrder.length-1, 0, inOrder.length-1);
    }
    private TreeNode reBuild(int[] preOrder, int[] inOrder, int l1, int r1, int l2, int r2) {
        if (l1 > r1 || l2 > r2 || (r1-l1)!=(r2-l2)) return null;
        if (l1 == r1) {
            return new TreeNode(preOrder[l1]);
        }
        int v = preOrder[l1];
        TreeNode root = new TreeNode(v);
        int mid = -1;
        // find the index of root in in-order array
        for (int i = l2; i <= r2; i++) {
            if (inOrder[i] == v) {
                mid = i;
                break;
            }
        }
        int leftLen = mid - l2;
        root.left = reBuild(preOrder, inOrder, l1 + 1, l1 + leftLen, l2, mid-1);
        root.right = reBuild(preOrder, inOrder, l1+1+leftLen, r1, mid+1, r2);
        return root;
    }
    public static void main(String[] args) {
        int[] preOrder = {4,3,2,1,5};
        int[] inOrder = {2,3,1,4,5};
        ReBuildTree reBuildTree = new ReBuildTree();
        List<Integer> postOrder = reBuildTree.buildPostOrder(preOrder, inOrder);
        for (int i : postOrder) {
            System.out.print(i + " ");
        }
        System.out.println();

        TreeNode root = reBuildTree.reBuild(preOrder, inOrder);
        List<Integer> nodes = new PostOrder().postOrderRecursive(root);
        for (int i : nodes) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
