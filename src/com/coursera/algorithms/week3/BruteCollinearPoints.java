package com.coursera.algorithms.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdj on 1/10/17.
 */
public class BruteCollinearPoints
{
    private LineSegment[] lineSegArr = null;
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        List<Point> pointList = new ArrayList<>();
        List<List<Point>> groupedPoints = new ArrayList<>();
        if(points == null)
        {
            throw new NullPointerException();
        }
        for (int i = 0 ; i < points.length ; i++)
        {
            if(points[i] == null)
            {
                throw  new NullPointerException();
            }
            for(int j = i+1 ; j < points.length ; j++)
            {
                if(points[i].compareTo(points[j]) == 0)
                {
                    throw new IllegalArgumentException();
                }
            }
        }


        double slopeToCheck = 0;
        Point temp1,temp2;
        for(int i = 0 ; i < points.length && pointList.size() < 4; i ++)
        {
            pointList.clear();
            pointList.add(points[i]);
            for (int j = 0 ; j < points.length && pointList.size() < 4; j++)
            {
                if(points[i].compareTo(points[j]) >= 0)
                    continue;
                pointList.add(points[j]);
                slopeToCheck = points[i].slopeTo(points[j]);

                for (int k = 0 ; k < points.length && pointList.size() < 4; k++)
                {
                    if( points[i].compareTo(points[k]) == 0 || points[j].compareTo(points[k]) == 0)
                    {
                        continue;
                    }
                    if( Math.abs(slopeToCheck - points[i].slopeTo(points[k])) <= 0.000001)
                    {
                        pointList.add(points[k]);
                    }
                }
                if (pointList.size() == 4)
                {
                    temp1 = pointList.get(2);
                    temp2 = pointList.get(3);
                    pointList.remove(2);
                    pointList.remove(3);
                    if(temp2.compareTo(temp1) == -1)
                    {
                        pointList.add(temp2);
                        pointList.add(temp1);
                    }else
                    {
                        pointList.add(temp1);
                        pointList.add(temp2);
                    }
                    groupedPoints.add(pointList);
                    pointList = new ArrayList<>();
                }
                if(pointList.size() < 4)
                {
                    pointList.clear();
                    if(j+1 != points.length)
                    {
                        pointList.add(points[i]);
                    }
                }
            }
        }
        lineSegArr = constructArr(groupedPoints);
    }
    public           int numberOfSegments()        // the number of line segments
    {
        return lineSegArr.length;
    }

    private double distance(Point p,Point q)
    {
        return Math.sqrt(Math.pow(p.getX()-q.getX(),2)+Math.pow(p.getY()-q.getY(),2));
    }

    private LineSegment[] constructArr(List<List<Point>> groupedPoints)
    {
        LineSegment retArr [] = new LineSegment[groupedPoints.size()];
        int counter = 0;
        List<Point> temp = null;
        Point p1 = null , q1= null;
        double maxDist = 0;
        List<Point> baseSet = new ArrayList<>();
        for ( List l : groupedPoints)
        {
            temp = l;
            for ( Point p : temp)
            {
                for ( Point q : temp)
                {
                    if(p.compareTo(q) == 0)
                    {
                        continue;
                    }
                    if ( this.distance(p,q) > maxDist)
                    {
                        maxDist = this.distance(p,q);
                        p1 = p;
                        q1 = q;
                    }
                }
            }
            if(! (baseSet.contains(p1) && baseSet.contains(q1)))
            {
                retArr[counter] = new LineSegment(p1,q1);
                counter++;
                baseSet.add(p1);
                baseSet.add(q1);
            }
        }
        return retArr;
    }
    public LineSegment[] segments()                // the line segments
    {
        return  lineSegArr;
    }

}
