package com.coursera.algorithms.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * Created by sdj on 1/10/17.
 */

public class FastCollinearPoints
{
    final private List<LineSegment> lineSegArr = new ArrayList<>();

    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
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
        Point basePoint = null;
        List<Point> selectedList = new ArrayList<>();
        List<Point> listedPoints = new ArrayList<>(Arrays.asList(points));
        List<Point> cloned = null;
        Collections.sort(listedPoints);
        for (int i = 0; i < listedPoints.size(); i++)
        {
            cloned = new ArrayList<>(listedPoints);
            cloned.remove(i);
            Collections.sort(cloned,listedPoints.get(i).slopeOrder());
            // Fixing the base point
            basePoint = listedPoints.get(i);


            // Sorting all points in map with respect to values
            Double value = null;
            int c = 0;
            selectedList.clear();
            for (Point p : cloned)
            {
                if(value == null)
                {
                    value = basePoint.slopeTo(p);
                    c++;
                    selectedList.add(p);
                }
                else if (Double.compare(value,basePoint.slopeTo(p)) == 0)
                {
                    c++;
                    selectedList.add(p);
                }
                else
                {
                    value = basePoint.slopeTo(p);
                    if(c<3)
                    {
                        c = 1;
                        selectedList.clear();
                        selectedList.add(p);
                        continue;
                    }
                    else if (!selectedList.isEmpty() && isPointSmallest(selectedList,basePoint))
                    {
                        lineSegArr.add(new LineSegment(basePoint,farthestPoint(selectedList,basePoint)));
                    }
                    c = 1;
                    selectedList.clear();
                    selectedList.add(p);
                }
            }
            if (!(c<3)  && !selectedList.isEmpty() && isPointSmallest(selectedList,basePoint))
            {
                lineSegArr.add(new LineSegment(basePoint,farthestPoint(selectedList,basePoint)));
            }
        }
    }

    private boolean isPointSmallest(List<Point> list, Point p)
    {
        for (Point q : list)
        {
            if (!(p.compareTo(q) < 0))
            {
                return false;
            }
        }
        return true;
    }

    private Point farthestPoint(List<Point> list, Point p)
    {
        Collections.sort(list);
        return list.get(list.size()-1);
    }

    public           int numberOfSegments()        // the number of line segments
    {
        return lineSegArr.size();
    }

    public LineSegment[] segments()                // the line segments
    {
        return lineSegArr.toArray(new LineSegment[lineSegArr.size()]);
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

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        System.out.println(collinear.segments().length);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
