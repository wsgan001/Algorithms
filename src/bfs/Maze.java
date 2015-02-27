package bfs;

import java.util.Scanner;

/**
 * Created by basin on 21/2/15.
 */
public class Maze {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] A = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                A[i][j] = in.nextInt();
            }
        }

    }
}
