package com.coursera.algorithms.week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by sdj on 12/25/16.
 */
public class Percolation
{
    private int n;
    private WeightedQuickUnionUF wfObj = null;
    private WeightedQuickUnionUF wfObjCopy = null;
    private boolean data[][];

    public Percolation(int n)
    {
        if(n<=0)
        {
            throw  new IllegalArgumentException();
        }
        this.n = n;
        wfObj = new WeightedQuickUnionUF((n*n)+2);
        wfObjCopy = new WeightedQuickUnionUF((n*n)+2);
        data = new boolean[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                data[i][j] = false;
    }

    private int oneDTranslator(int row,int col)
    {
        return ((row-1)*n)+col-1;
    }

    private void union(int oneDrep,int row,int col)
    {
        if(data[row-1][col-1]){
            wfObj.union(oneDrep,oneDTranslator(row,col));
            wfObjCopy.union(oneDrep,oneDTranslator(row,col));
        }
    }

    private void cornerCase(int oneDrep,int row,int col)
    {
        if(col-1 == 0 ){
            this.union(oneDrep,row,col+1);
        }else if(col-1==n-1){
            this.union(oneDrep,row,col-1);
        }else{
            this.union(oneDrep,row,col-1);
            this.union(oneDrep,row,col+1);
        }
    }
    public void open(int row, int col)     // open site (row, col) if it is not open already
    {
        if(row > n || col > n || row < 1 || col < 1){
            throw new IndexOutOfBoundsException();
        }
        this.data[row-1][col-1] = true;
        int oneDrep = oneDTranslator(row,col);
        if(n==1){
            this.union(1,1,1);
            this.union(2,1,1);
            return;
        }
        // Connecting to virtual layers
        if(row==1){
            this.union((n*n),row,col);
        }else if(row==n){
            wfObjCopy.union((n*n)+1,oneDTranslator(row,col));
        }

        // Connecting to top down left right
        if(row-1 == 0){
            this.union(oneDrep,row+1,col);
            cornerCase(oneDrep,row,col);
        }else if(row-1==n-1){
            this.union(oneDrep,row-1,col);
            cornerCase(oneDrep,row,col);
        }else if(col-1==0){
            this.union(oneDrep,row+1,col);
            this.union(oneDrep,row-1,col);
            this.union(oneDrep,row,col+1);
        }else if(col-1==n-1){
            this.union(oneDrep,row+1,col);
            this.union(oneDrep,row-1,col);
            this.union(oneDrep,row,col-1);
        } else{
            this.union(oneDrep,row-1,col);
            this.union(oneDrep,row,col-1);
            this.union(oneDrep,row,col+1);
            this.union(oneDrep,row+1,col);
        }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return this.data[row-1][col-1];
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if(this.data[row-1][col-1] && this.wfObj.connected(n*n,oneDTranslator(row,col)))
            return true;
        return false;
    }
    public boolean percolates()                 // does the system percolate?
    {
        if((n==1 && data[0][0]) || this.wfObjCopy.connected((n*n),(n*n)+1))
            return true;
        return false;
    }

    public int numberOfOpenSites()
    {
        int count = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                count += (data[i][j])?1:0;
            }
        }
        return  count;
    }
    public static void main(String[] args)   // test client (optional)
    {
        int n = StdIn.readInt();
        Percolation percObj = new Percolation(n);
        int a,b;
//        int i=0;
//        PercolationVisualizer percVis = new PercolationVisualizer();
        while(!StdIn.isEmpty()){
            a = StdIn.readInt();
            b=StdIn.readInt();
//            if(i!=33)
                percObj.open(a,b);
//            if(i==33){
//                percObj.open(a,b);
//                percVis.draw(percObj,n);
//            }
            // i++;
          //  System.out.println(percObj.isFull(1,1));
        }
        System.out.println(percObj.percolates());
    }
}
