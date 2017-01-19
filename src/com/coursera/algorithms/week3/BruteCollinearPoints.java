package com.coursera.algorithms.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by sdj on 1/10/17.
 */
public class BruteCollinearPoints
{

    final private List<LineSegment> lineSegList;
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        final LineSegment[] lineSegArr;
        List<Point> pointList = new ArrayList<>();
        List<List<Point>> groupedPoints = new ArrayList<>();
        List<Point> mainPoint = new ArrayList<>(Arrays.asList(points));
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
        for(int i = 0 ; i < mainPoint.size() && pointList.size() < 4; i ++)
        {
            pointList.clear();
            pointList.add(mainPoint.get(i));
            for (int j = 0 ; j < mainPoint.size() && pointList.size() < 4; j++)
            {
                if(mainPoint.get(i).compareTo(mainPoint.get(j)) >= 0)
                    continue;
                pointList.add(mainPoint.get(j));
                slopeToCheck = mainPoint.get(i).slopeTo(mainPoint.get(j));

                for (int k = 0 ; k < mainPoint.size() && pointList.size() < 4; k++)
                {
                    if( mainPoint.get(i).compareTo(mainPoint.get(k)) == 0 || mainPoint.get(j).compareTo(mainPoint.get(k)) == 0)
                    {
                        continue;
                    }
                    if(Double.compare(slopeToCheck,mainPoint.get(i).slopeTo(mainPoint.get(k))) == 0)
                    {
                        pointList.add(mainPoint.get(k));
                    }
                }
                if (pointList.size() == 4)
                {
                    temp1 = pointList.get(2);
                    temp2 = pointList.get(3);
                    pointList.remove(3);
                    pointList.remove(2);
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
                    if(j+1 != mainPoint.size())
                    {
                        pointList.add(mainPoint.get(i));
                    }
                }
            }
        }
        lineSegArr = constructArr(groupedPoints);
        lineSegList = Arrays.asList(lineSegArr);
    }
    public           int numberOfSegments()        // the number of line segments
    {
        return lineSegList.size();
    }

    private LineSegment[] constructArr(List<List<Point>> groupedPoints)
    {
        LineSegment retArr [] = new LineSegment[groupedPoints.size()];
        int counter = 0;
        List<Point> temp = null;
        Point p1 = null , q1= null;
        double maxDist = 0;
        List<Point> baseSet = new ArrayList<>();
        for ( List<Point> l : groupedPoints)
        {
            temp = l;

            Collections.sort(temp);
            p1 = temp.get(0);
            q1 = temp.get(temp.size()-1);
            if(! (baseSet.contains(p1) && baseSet.contains(q1)))
            {
                retArr[counter] = new LineSegment(p1,q1);
                counter++;
                baseSet.add(p1);
                baseSet.add(q1);
            }
        }
        LineSegment [] tempArr = new LineSegment[counter];
        int i=0;
        while(i<counter)
        {
            tempArr[i] = retArr[i];
            i++;
        }
        return tempArr;
    }
    public LineSegment[] segments()                // the line segments
    {
        return  (LineSegment[]) lineSegList.toArray();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        /*// draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        System.out.println(collinear.segments().length);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        System.out.println(collinear.segments());
        /*System.out.println(collinear.numberOfSegments());
        for(int i=0;i<9;i++)
        {
            System.out.println(collinear.segments());
        }*/



        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        System.out.println(collinear.numberOfSegments());
        System.out.println(collinear.segments());
        points[1] = new Point(12,23);
        System.out.println(collinear.segments()[1].toString());
        collinear.segments()[1] = new LineSegment(new Point(1,2),new Point(3,4));
        System.out.println(collinear.segments()[1].toString());
        System.out.println();
        System.out.println();
    }

}
