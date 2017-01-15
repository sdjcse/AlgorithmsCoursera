package com.coursera.algorithms.week3;

import java.util.Comparator;

/**
 * Created by sdj on 1/10/17.
 */
public class Point
{
    int x,y ;
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public Point(int x, int y)                         // constructs the point (x, y)
    {
        this.x = x;
        this.y = y;
    }

    public   void draw()                               // draws this point
    {

    }
    public   void drawTo(Point that)                   // draws the line segment from this point to that point
    {

    }
    public String toString()                           // string representation
    {
        return "x:" + Integer.toString(x) + ", y: " + Integer.toString(y);
    }

    public int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
    {
        return (this.getY() < that.getY() || (this.getY() == that.getY() && this.getX() < that.getX())) ? -1 : (this.getX() == that.getX() && this.getY() == that.getY()) ? 0 : +1;
    }
    public            double slopeTo(Point that)       // the slope between this point and that point
    {
        double nr = (double) (that.getY()-this.getY());
        double dr = (double) (that.getX()-this.getX());

        if(nr==0 && dr == 0)
        {
            return -Double.MAX_VALUE;
        }
        else if(nr==0)
        {
            return +0;
        }
        else if(dr==0)
        {
            return Double.MAX_VALUE;
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
