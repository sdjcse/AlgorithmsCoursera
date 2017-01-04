package com.coursera.algorithms.week1;

import edu.princeton.cs.algs4.*;


/**
 * Created by sdj on 12/26/16.
 */
public class PercolationStats {
    private int row,col;
    private int n;
    private int trails;
    private double [] succTrial = null;


    public PercolationStats(int n,int trails){
        Percolation percObj;
        if(n<=0 || trails <=0)
            throw new IllegalArgumentException();
        this.n = n;
        this.trails = trails;
        succTrial = new double[trails];
        int i = 0;
        while(trails>0){
            percObj = new Percolation(n);
            while(!percObj.percolates()){
                //twoDtranslator(StdRandom.uniform(n*n));
                percObj.open(StdRandom.uniform(1,n+1),StdRandom.uniform(1,n+1));
            }
            succTrial[i] = ((double)this.getNumberOpen(percObj))/((double)n*n);
            i++;
            trails--;
        }
    }

    private int getNumberOpen(Percolation percObj){
        int count=0;
        for(int i=0;i<n;i++){
         for(int j=0;j<n;j++){
             if(percObj.isOpen(i+1,j+1)){
                 count++;
             }
         }
        }
        return count;
    }
    public double mean(){
        return StdStats.mean(this.succTrial);
    }
    public double stddev(){
        return  StdStats.stddev(this.succTrial);
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return (StdStats.mean(succTrial) - (1.96*StdStats.stddev(succTrial)/Math.sqrt(this.trails)));
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return (StdStats.mean(succTrial) + (1.96*StdStats.stddev(succTrial)/Math.sqrt(this.trails)));
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
