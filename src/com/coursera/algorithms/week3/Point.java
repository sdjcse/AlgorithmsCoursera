package com.coursera.algorithms.week3;

import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

/**
 * Created by sdj on 1/10/17.
 */
public class Point implements Comparable<Point>
{
    private final int x,y ;

    public Point(int x, int y)                         // constructs the point (x, y)
    {
        this.x = x;
        this.y = y;
    }

    public   void draw()                               // draws this point
    {
        StdDraw.point(x, y);
    }
    public   void drawTo(Point that)                   // draws the line segment from this point to that point
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString()                           // string representation
    {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
    {
        return (this.y < that.y || (this.y == that.y && this.x < that.x)) ? -1 : (this.x == that.x && this.y == that.y) ? 0 : +1;
    }
    public            double slopeTo(Point that)       // the slope between this point and that point
    {
        double nr = (double) (that.y-this.y);
        double dr = (double) (that.x-this.x);

        if(nr==0 && dr == 0)
        {
            return -1*Double.POSITIVE_INFINITY;
        }
        else if(nr==0)
        {
            return +0;
        }
        else if(dr==0)
        {
            return Double.POSITIVE_INFINITY;
        }
        return nr/dr;
    }
    public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
    {
        return new Comparator<Point>()
        {
            @Override
            public int compare(Point point, Point t1)
            {
                return Double.compare(slopeTo(point),slopeTo(t1));
            }
        };
    }
}
