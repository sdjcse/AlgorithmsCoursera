import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by sdj on 12/25/16.
 */
public class Percolation
{
    int n;
    WeightedQuickUnionUF wfObj = null;
    boolean data[][];
    int numberOpen = 0;

    public Percolation(int n){
        if(n<=0){
            throw  new IllegalArgumentException();
        }
        this.n = n;
        wfObj = new WeightedQuickUnionUF(n*n);
        data = new boolean[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                data[i][j] = false;
    }

    public int getNumberOpen(){
        return  this.numberOpen;
    }
    public int oneDTranslator(int row,int col){
        return ((row-1)*n)+col-1;
    }

    public void union(int oneDrep,int row,int col){
        if(data[row-1][col-1]){
            //System.out.println(oneDrep);
            //System.out.println(row + " " + col);
            //System.out.println(oneDTranslator(row,col));
            wfObj.union(oneDrep,oneDTranslator(row,col));
        }
    }

    public void cornerCase(int oneDrep,int row,int col){
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
        numberOpen++;
        int oneDrep = oneDTranslator(row,col);
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
        for(int i=0;i<n;i++){
            if(this.wfObj.connected(oneDTranslator(1,i+1),oneDTranslator(row,col)))
                return true;
        }
        return false;
    }
    public boolean percolates()              // does the system percolate?
    {
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(this.wfObj.connected(oneDTranslator(n,i+1),oneDTranslator(1,j+1)))
                    return true;
        return false;
    }


    public static void main(String[] args)   // test client (optional)
    {
        int n = StdIn.readInt();
        Percolation percObj = new Percolation(n);
        while(!StdIn.isEmpty()){
            percObj.open(StdIn.readInt(),StdIn.readInt());
        }
        System.out.println(percObj.percolates());
    }
}
