
/**
 * **************************************************
 * Created by basin on 15-1-29.
 * **************************************************
 */
public class PercolationStats {
    private int N;
    private int T;
    private long[] count;
    private double[] fractions;
    private double mean;
    private double stddev;
    private boolean meanB;
    private boolean stddevB;
    public PercolationStats(int N, int T) {
        if(N<=0 || T<=1) throw new IllegalArgumentException();
        this.N = N;
        this.T = T;
        count = new long[T];
        fractions = new double[T];
        for(int k = 0; k< T; k++) {
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates()) {
                int i = (int)(Math.random()*N + 1);
                int j = (int)(Math.random()*N + 1);
                if(!percolation.isOpen(i,j)) {
                    percolation.open(i,j);
                    count[k]++;
                }
            }
        }
    }
    public double mean() {
        if(meanB){
            return this.mean;
        }
        double m = 0.0;
        for(int i = 0; i< T; i++) {
            fractions[i] =(double)count[i]/(N*N);
            m += fractions[i];
        }
        this.mean = m/T;
        this.meanB = true;
        return this.mean;
    }
    public double stddev() {
        if(stddevB) {
            return this.stddev;
        }
        double dev = 0.0;
        double m = mean();
        for(int i = 0; i< T; i++) {
            dev += (m-fractions[i])*(m-fractions[i]);
        }
        this.stddev = Math.sqrt(dev/(T-1));
        this.stddevB = true;
        return this.stddev;
    }
    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt((double)T);
    }
    public double confidenceHi() {
        return mean() + 1.96*stddev()/Math.sqrt((double)T);
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(N,T);
        System.out.print("mean:\t\t\t\t\t");
        System.out.print("= "+p.mean()+"\n");
        System.out.print("stddev:\t\t\t\t\t");
        System.out.print("= "+p.stddev()+"\n");
        System.out.print("95% confidence interval\t\t\t");
        System.out.print("= "+p.confidenceLo()+",");
        System.out.print(p.confidenceHi()+"\n");
    }
}
