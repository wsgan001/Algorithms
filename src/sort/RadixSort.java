package sort;

/**
 * Created by basin on 17/2/15.
 */
public class RadixSort {
    private static boolean isSorted(int[] A) {
        for (int i = 1; i< A.length; i++) {
            if (A[i] < A[i-1]) return false;
        }
        return true;
    }
    private static class Node {
        int key;
        int link;
        public Node(int k, int link) {
            this.key = k;
            this.link = link;
        }
    }

    /**
     * get the number of ith
     * @param n
     * @param i
     * @return the ith number of integer n
     */
    private static int getDigit(int n, int i) {
        for (int j = 1; j < i ; j++) {
            n /= 10;
        }
        return n%10;
    }
    public static void LSD(int[] A, int d) {
        if (A == null || A.length <= 1) return;
        Node[] nodes = new Node[A.length+1];
        nodes[0] = new Node(0,1);
        for (int i = 0; i < A.length; i++) {
            nodes[i+1] = new Node(A[i], i+2);
        }
        nodes[A.length].link = 0;
        int[] front = new int[10], rear = new int[10];
        for (int i = 0; i< d; i++) {
            // distribute
            for (int j = 0; j< 10; j++) front[j] = 0;
            for (int cur = nodes[0].link; cur != 0; cur = nodes[cur].link) {
                int k = getDigit(nodes[cur].key, i+1);
                if (front[k] == 0) {
                    front[k] = cur;
                    rear[k] = cur;
                } else {
                    nodes[rear[k]].link = cur;
                    rear[k] = cur;
                }
            }
            // collect
            int j = 0;
            while (front[j] == 0) j++;
            nodes[0].link = front[j];
            int cur = front[j];
            int last = rear[j];
            for (j = j +1; j < 10; j++){
                if (front[j] != 0) {
                    nodes[last].link = front[j];
                    last = rear[j];
                }
            }
            nodes[last].link = 0;
        }
        int i = nodes[0].link, j = 0;
        while (i != 0) {
            A[j++] = nodes[i].key;
            i = nodes[i].link;
        }
    }

    public static void main( String[] args) {
        int[] A = {278, 109, 63, 930, 589, 184, 505, 269, 8, 83};
        RadixSort.LSD(A, 3);
        System.out.println(isSorted(A));
    }
}
