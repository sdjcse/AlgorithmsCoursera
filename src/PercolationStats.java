import edu.princeton.cs.algs4.*;


/**
 * Created by sdj on 12/26/16.
 */
public class PercolationStats {
    int row,col;
    int n;
    int trails;
    double [] succTrial = null;

    public PercolationStats(int n,int trails){
        if(n<=0 || trails <=0)
            throw new IllegalArgumentException();
        this.n = n;
        this.trails = trails;
        Percolation percObj;
        succTrial = new double[trails];
        int i = 0;
        while(trails>0){
            percObj = new Percolation(n);
            while(!percObj.percolates()){
                twoDtranslator(StdRandom.uniform(0,n*n));
                percObj.open(this.row,this.col);
            }
            succTrial[i] = percObj.getNumberOpen()/(n*n);
            i++;
            trails--;
        }
    }

    // send as zero index
    public void twoDtranslator(double q){
        this.row = (int)q/n;
        this.row++;
        this.col = (int)q%20;
        this.col++;
    }
    public double mean(){
        return StdStats.mean(this.succTrial);
    }
    public double stddev(){
        return  StdStats.stddev(this.succTrial);
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return (StdStats.mean(succTrial) - (1.96*StdStats.stddev(succTrial)/Math.pow(this.trails,1/2)));
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return (StdStats.mean(succTrial) + (1.96*StdStats.stddev(succTrial)/Math.pow(this.trails,1/2)));
    }

    public static void main(String[] args){
        Stopwatch stopwatch = new Stopwatch();
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats newObj = new PercolationStats(n,t);
         System.out.println("mean = " + newObj.mean());
        System.out.println("stddev = "+newObj.stddev());
        System.out.println("95% confidence interval = "+newObj.confidenceLo() + ", " + newObj.confidenceHi());
        System.out.println(stopwatch.elapsedTime());
    }
}
