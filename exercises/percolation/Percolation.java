/**
 * **************************************************
 * Created by basin on 15-1-29.
 * **************************************************
 */
public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF ufBackwash;
    private boolean[][] grid;
    private int N;
    public Percolation(int N) {
        this.N = N;
        if (N <= 0) throw new IllegalArgumentException("N must be bigger than 0");
        grid = new boolean[N+2][N+2];
        weightedQuickUnionUF = new WeightedQuickUnionUF((N + 2) * (N + 2));
        ufBackwash = new WeightedQuickUnionUF((N + 1) * (N + 2));
        // set the line 0 connected line 1
        for (int i = 1; i <= N; i++) {
            weightedQuickUnionUF.union(N + 2 + i, 0);
            ufBackwash.union(N + 2 + i , 0);
        }
        // set the line N connected line N+!
        for(int i = 1; i<= N; i++) {
            weightedQuickUnionUF.union(N * (N + 2) + i, (N + 1) * (N + 2));
        }
    }
    public void open(int i, int j) {
        if(i<1 || j<1 || i>N || j>N) throw new IndexOutOfBoundsException();
        if(grid[i][j]) return;
        // set the (i,j) open
        grid[i][j] = true;
        // union the up, down, left, right
        int index = i * (N + 2) + j;
        if (i-1 >= 1 && grid[i-1][j]) {
            weightedQuickUnionUF.union((i - 1) * (N + 2) + j, index);
            ufBackwash.union((i - 1) * (N + 2) + j, index);
        }
        if (i+1<=N && grid[i+1][j]) {
            weightedQuickUnionUF.union((i + 1) * (N + 2) + j, index);
            ufBackwash.union((i + 1) * (N + 2) + j, index);
        }
        if (j-1>=1 && grid[i][j-1]) {
            weightedQuickUnionUF.union(i*(N+2)+j-1, index);
        }
        if (j + 1 <= N && grid[i][j+1]) {
            weightedQuickUnionUF.union(i * (N + 2) + j + 1, index);
            ufBackwash.union(i * (N + 2) + j + 1, index);
        }
    }
    public boolean isOpen(int i, int j) {
        if(i<1 || j<1 || i>N || j>N) throw new IndexOutOfBoundsException();
        return grid[i][j];
    }
    public boolean isFull(int i, int j) {
        if(i<1 || j<1 || i>N || j>N) throw new IndexOutOfBoundsException();
        if(grid[i][j]){
            return ufBackwash.connected(i*(N+2)+j, 0);
        }
        return false;
    }
    public boolean percolates() {
        if(N == 1) return grid[1][1];
        return weightedQuickUnionUF.connected(0, (N+1)*(N+2));
    }
//    public static void main(String[] args) {
//
//    }
}